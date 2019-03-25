/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.content.Intent

fun showDebugSettings(activity: BaseActivity) {
    Intent(activity, DebugSettingsActivity::class.java)
            .let(activity::startActivity)
}
