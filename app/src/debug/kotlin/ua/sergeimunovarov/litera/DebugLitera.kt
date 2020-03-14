/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import androidx.preference.PreferenceManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import leakcanary.AppWatcher

@FlowPreview
@ExperimentalCoroutinesApi
class DebugLitera : Litera() {

    override fun onCreate() {
        super.onCreate()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean(KEY_ENABLE_LC, false)) {
            AppWatcher.config = AppWatcher.config.copy(enabled = false)
        }
    }

    companion object {
        const val KEY_ENABLE_LC = "enable_lc"
    }
}
