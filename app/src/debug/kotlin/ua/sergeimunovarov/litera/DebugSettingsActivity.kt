/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.preference.PreferenceManager
import leakcanary.AppWatcher

class DebugSettingsActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_settings)

        val prefs = PreferenceManager.getDefaultSharedPreferences(app())

        findViewById<TextView>(R.id.adsBuildInfo).text = "Build date: ${BuildConfig.BUILD_DATE}"

        findViewById<CheckBox>(R.id.adsLeakcanaryCheck).apply {
            isChecked = prefs.getBoolean(DebugLitera.KEY_ENABLE_LC, false)
            setOnCheckedChangeListener { _, isChecked ->
                AppWatcher.config = AppWatcher.config.copy(enabled = isChecked)
                prefs.edit().putBoolean(DebugLitera.KEY_ENABLE_LC, isChecked).apply()
            }
        }
    }

}
