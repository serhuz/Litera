/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translator.ukr

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ua.sergeimunovarov.litera.TestData
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorGost779Ukr

@RunWith(Parameterized::class)
class TranslatorGost779UkrTest constructor(private val data: TestData) {

    private val translator = TranslatorGost779Ukr()

    @Test
    fun replaceChars() {
        val actual = translator.replaceCharacters(data.given)

        assertThat(actual).isEqualTo(data.expected)
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<TestData> = listOf(
                TestData("Д", "D"),
                TestData("1", "1"),
                TestData("в", "v"),
                TestData("Ц", "Cz"),
                TestData("цІ", "cI"),
                TestData("Ясь", "Yas`"),
                TestData("Вар'ят", "Var'yat"),
                TestData("Юрій", "Yurij"),
                TestData("Юріївський", "Yuriyivs`ky`j"),
                TestData("Кирилівка", "Ky`ry`livka"),
                TestData("Їдальня", "Yidal`nya"),
                TestData("Їдальня-2", "Yidal`nya-2"),
                TestData("", "")
        )
    }
}
