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
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorUsbgnUkr

@RunWith(Parameterized::class)
class TranslatorUsbgnUkrTest constructor(private val data: TestData) {

    private val translator = TranslatorUsbgnUkr()

    @Test
    fun replaceChars() {
        val actual = translator.replaceCharacters(data.given)

        assertThat(actual).isEqualTo(data.expected)
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<TestData> = listOf(
                TestData("Ясь", "Yas'"),
                TestData("Вар'ят", "Var\"yat"),
                TestData("Юрій", "Yuriy"),
                TestData("Юріївський", "Yuriyivs'kyy"),
                TestData("Кирилівка", "Kyrylivka"),
                TestData("Їдальня", "Yidal'nya"),
                TestData("Їдальня-2", "Yidal'nya-2"),
                TestData("", "")
        )
    }
}
