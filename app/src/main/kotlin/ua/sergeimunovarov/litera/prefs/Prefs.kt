/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.prefs

import android.content.SharedPreferences

class Prefs(private val preferences: SharedPreferences) {

    var setting: Setting
        get() = preferences.getString(keySetting, defaultStandard)!!.fromJson()
        set(value) = preferences.edit().putString(keySetting, value.toJson()).apply()

}

private const val keySetting = "setting"
private const val defaultStandard = "{\"standard\":\"GOST_R_52535\"}"
