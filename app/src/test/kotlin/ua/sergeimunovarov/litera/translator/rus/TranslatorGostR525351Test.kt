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
import ua.sergeimunovarov.litera.translit.translator.rus.TranslatorGostR525351

@RunWith(Parameterized::class)
class TranslatorGostR525351Test constructor(private val data: TestData) {

    private val translator = TranslatorGostR525351()

    @Test
    fun replaceChars() {
        val actual = translator.replaceCharacters(data.given)

        assertThat(actual).isEqualTo(data.expected)
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<TestData> = listOf(
                TestData("Жаров", "ZHAROV"),
                TestData("Щукин", "SHCHUKIN"),
                TestData("Лебедев", "LEBEDEV"),
                TestData("Синицын", "SINITCYN"),
                TestData("Цаплин", "TCAPLIN"),
                TestData("", "")
        )
    }

}
