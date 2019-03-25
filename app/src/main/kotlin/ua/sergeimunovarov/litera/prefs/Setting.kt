/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.prefs

import com.eclipsesource.json.Json
import ua.sergeimunovarov.litera.db.Standard

data class Setting(val standard: Standard)

fun Setting.toJson(): String = Json.`object`()
        .add("setting", standard.name)
        .toString()

fun String.fromJson(): Setting = Json.parse(this)
        .asObject()
        .run {
            Setting(Standard.valueOf(getString("setting", Standard.GOST_R_52535.name)))
        }
