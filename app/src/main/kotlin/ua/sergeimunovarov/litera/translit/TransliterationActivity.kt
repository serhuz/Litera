/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ua.sergeimunovarov.litera.BaseActivity
import ua.sergeimunovarov.litera.R
import ua.sergeimunovarov.litera.databinding.ActivityTransliterationBinding
import ua.sergeimunovarov.litera.util.applyVisibilityAdListener
import ua.sergeimunovarov.litera.util.loadAdWithDefaultRequest

@FlowPreview
@ExperimentalCoroutinesApi
class TransliterationActivity : BaseActivity() {

    private val viewModel: TransliterationActivityViewModel by stateViewModel()

    private lateinit var binding: ActivityTransliterationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transliteration)
        setResult(Activity.RESULT_CANCELED)

        initBinding()
        initViewModel()
    }

    private fun initBinding() {
        binding.apply {
            lifecycleOwner = this@TransliterationActivity
            model = viewModel
            adView.apply { applyVisibilityAdListener() }.loadAdWithDefaultRequest()
            input.showSoftInputOnFocus = true
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            intent?.getStringExtra("text")?.let(input::setValue)

            exitEvent.observe(this@TransliterationActivity, Observer {
                this.takeIf { it.hasText() }
                        ?.let {
                            val intent = with(Intent()) {
                                putExtra("input", viewModel.input.value)
                                putExtra("romanized", viewModel.romanized.value)
                            }
                            setResult(Activity.RESULT_OK, intent)
                        }
                hideKeyboard()
                finish()
            })
        }
    }

    private fun hideKeyboard() {
        val token = currentFocus?.windowToken ?: binding.input.windowToken
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(token, 0)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.stay, R.anim.slide_out_down)
    }
}
