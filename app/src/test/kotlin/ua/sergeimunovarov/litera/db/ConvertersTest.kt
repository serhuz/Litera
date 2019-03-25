/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.db

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test

class ConvertersTest {

    @Test
    fun convertFromLang() {
        val actual = Converters.fromLang(Lang.RU)

        assertThat(actual).isNotBlank().isEqualTo("RU")
    }

    @Test
    fun convertFromNullLang() {
        val actual = Converters.fromLang(null)

        assertThat(actual).isNull()
    }

    @Test
    fun convertToLang() {
        val actual = Converters.toLang("UK")

        assertThat(actual).isNotNull().isEqualTo(Lang.UK)
    }

    @Test
    fun convertToNullLang() {
        val actual = Converters.toLang(null)

        assertThat(actual).isNull()
    }
}
