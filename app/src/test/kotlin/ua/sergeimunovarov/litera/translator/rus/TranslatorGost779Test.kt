/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translator.rus

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ua.sergeimunovarov.litera.TestData
import ua.sergeimunovarov.litera.translit.translator.rus.TranslatorGost779

@RunWith(Parameterized::class)
class TranslatorGost779Test constructor(private val data: TestData) {

    private val translator = TranslatorGost779()

    @Test
    fun replaceChars() {
        val actual = translator.replaceCharacters(data.given)

        assertThat(actual).isEqualTo(data.expected)
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<TestData> = listOf(
                TestData("Щукино", "Shhukino"),
                TestData("Цаплино", "Czaplino"),
                TestData("Центральное", "Central`noe"),
                TestData("Центральное-2", "Central`noe-2"),
                TestData("Яблоково", "Yablokovo"),
                TestData("Харьков", "Xar`kov"),
                TestData("", "")
        )
    }
}
