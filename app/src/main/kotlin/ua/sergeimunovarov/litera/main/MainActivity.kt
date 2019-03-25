/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.main

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ua.sergeimunovarov.litera.*
import ua.sergeimunovarov.litera.databinding.ActivityMainBinding
import ua.sergeimunovarov.litera.db.Item
import ua.sergeimunovarov.litera.db.getDistinctLanguages
import ua.sergeimunovarov.litera.main.history.HistoryAdapter
import ua.sergeimunovarov.litera.main.history.HistoryViewHolder
import ua.sergeimunovarov.litera.main.history.SwipeToDeleteHandler
import ua.sergeimunovarov.litera.translit.TransliterationActivity
import ua.sergeimunovarov.litera.util.VoiceRecognitionUtil
import ua.sergeimunovarov.litera.util.applyVisibilityAdListener
import ua.sergeimunovarov.litera.util.loadAdWithDefaultRequest
import ua.sergeimunovarov.litera.views.withBackgroundColor
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : BaseActivity() {

    @Inject
    lateinit var events: Events

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        app().initMainComponent(this).inject(this)
        OssLicensesMenuActivity.setActivityTitle(getString(R.string.menu_oss_licenses))

        initAdapter()
        initBinding()
        initViewModel()
    }

    private fun initAdapter() {
        adapter = HistoryAdapter().apply {
            itemLiveData.observe(this@MainActivity, Observer(this@MainActivity::copyData))
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    binding.historyItems.layoutManager?.scrollToPosition(0)
                }
            })
        }
    }

    private fun initBinding() {
        binding.apply {
            lifecycleOwner = this@MainActivity
            model = viewModel
            adView.apply { applyVisibilityAdListener() }.loadAdWithDefaultRequest()
            input.showSoftInputOnFocus = false
            historyItems.adapter = adapter
            SwipeToDeleteHandler<HistoryViewHolder> { viewModel.removeItem(it.item) }
                    .let(::ItemTouchHelper)
                    .attachToRecyclerView(historyItems)
        }
        setSupportActionBar(binding.toolbar)
    }

    private fun initViewModel() {
        viewModel.apply {
            langSettingsClickEvent.observe(this@MainActivity, Observer {
                PopupMenu(this@MainActivity, binding.langSetting).also {
                    val langs = getDistinctLanguages().apply {
                        forEachIndexed { index, lang -> it.menu.add(Menu.NONE, index, index, lang.name) }
                    }
                    it.setOnMenuItemClickListener { item ->
                        setFirstAvailableStandardForLang(langs[item.itemId].name)
                        true
                    }
                }.show()
            })
            standardSettingsClickEvent.observe(this@MainActivity, Observer {
                PopupMenu(this@MainActivity, binding.standardSetting).apply {
                    val standards = getStandardsForCurrentLang()
                    standards.forEachIndexed { index, standard ->
                        menu.add(Menu.NONE, index, index, standard.displayName)
                    }
                    setOnMenuItemClickListener {
                        setStandard(standards[it.itemId])
                        true
                    }
                }.show()
            })
            inputClickEvent.observe(this@MainActivity, Observer {
                // todo set transition
                val intent = Intent(this@MainActivity, TransliterationActivity::class.java)
                startActivityForResult(intent, CODE_TRANSLIT)
            })
            cameraRecognitionEvent.observe(this@MainActivity, Observer {
                events.logCameraRecognition()
                // todo impl
            })
            voiceRecognitionEvent.observe(this@MainActivity, Observer {
                val hasVoiceRecognition = VoiceRecognitionUtil.voiceRecognitionPresent(packageManager)
                events.logVoiceRecognition(hasVoiceRecognition)
                if (hasVoiceRecognition) {
                    val intent = VoiceRecognitionUtil.createRecognizerIntent(packageName, getCurrentLang())
                    startActivityForResult(intent, CODE_VOICE_RECOGNITION)
                } else {
                    Snackbar.make(
                            binding.coordinator,
                            getString(R.string.no_voice_recognition),
                            Snackbar.LENGTH_SHORT
                    ).withBackgroundColor(R.color.primary).show()
                }
            })
            historyItems.observe(this@MainActivity, Observer {
                adapter.submitList(it)
                itemsLoaded.value = true
            })
        }
    }

    private fun copyData(item: Item) {
        ClipData.newPlainText(getString(R.string.litera_text_copy_label), item.romanized)
                .let((getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)::setPrimaryClip)
        events.logTextCopied()
        Snackbar.make(
                binding.coordinator,
                getString(R.string.text_copied),
                Snackbar.LENGTH_SHORT
        ).withBackgroundColor(R.color.primary).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?) = run {
        menuInflater.inflate(R.menu.menu_main, menu)
        true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = run {
        when (item?.itemId) {
            R.id.mmLicence ->
                Intent(this, OssLicensesMenuActivity::class.java).let(this::startActivity)
            R.id.mmPrivacyPolicy ->
                with(Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.PRIVACY_POLICY_URL))) {
                    if (resolveActivity(packageManager) != null) {
                        startActivity(this)
                    }
                }
            R.id.mmDebugSettings ->
                showDebugSettings(this)
            else ->
                error("Unexpected item id")
        }
        true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK != resultCode) return
        when (requestCode) {
            CODE_VOICE_RECOGNITION -> {
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        ?.takeIf { it.isNotEmpty() }
                        ?.let {
                            val intent = Intent(this, TransliterationActivity::class.java)
                                    .apply { putExtra("text", it[0]) }
                            startActivityForResult(intent, CODE_TRANSLIT)
                        }
                        ?: error("No data present")
            }
            CODE_CAMERA_RECOGNITION -> {
                data?.getStringExtra("text")
                        ?.takeIf { it.isNotBlank() }
                        ?.let {
                            val intent = with(Intent(this, TransliterationActivity::class.java)) {
                                putExtra("text", it)
                            }
                            startActivityForResult(intent, CODE_TRANSLIT)
                        }
                        ?: error("No data present")
            }
            CODE_TRANSLIT -> {
                data?.let {
                    val input = it.getStringExtra("input")!!
                    val romanized = it.getStringExtra("romanized")!!
                    viewModel.saveResult(input, romanized)
                } ?: error("No data present")
            }
            else -> error("Unexpected request code: $requestCode")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        app().releaseMainComponent()
    }

    companion object {
        private const val CODE_VOICE_RECOGNITION = 2000
        private const val CODE_CAMERA_RECOGNITION = 2001
        private const val CODE_TRANSLIT = 2002
    }
}
