/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.prefs

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test
import ua.sergeimunovarov.litera.FileUtil.aJson
import ua.sergeimunovarov.litera.db.Standard

class SettingTest {

    @Test
    fun parseJson() {
        val actual = aJson("standard.json").fromJson()

        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("standard", Standard.GOST_779_RU)
    }

    @Test
    fun writeJson() {
        val expected = aJson("standard.json")

        val actual = Setting(Standard.GOST_779_RU).toJson()

        assertThat(actual).isNotBlank().isEqualTo(expected)
    }
}
