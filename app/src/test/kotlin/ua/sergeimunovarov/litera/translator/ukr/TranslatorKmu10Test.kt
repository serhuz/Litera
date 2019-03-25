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
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorKmu10

@RunWith(Parameterized::class)
class TranslatorKmu10Test constructor(private val data: TestData) {

    private val translator = TranslatorKmu10()

    @Test
    fun replaceChars() {
        val actual = translator.replaceCharacters(data.given)

        assertThat(actual).isEqualTo(data.expected)
    }

    companion object {

        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<TestData> = listOf(
                TestData("Гадяч", "Hadiach"),
                TestData("Згурський", "Zghurskyi"),
                TestData("Гадяч Згурський", "Hadiach Zghurskyi"),
                TestData(" Гадяч    Згурський  ", "Hadiach Zghurskyi"),
                TestData("Єнакієве", "Yenakiievе"),
                TestData("Короп'є", "Koropie"),
                TestData("Короп'є 10", "Koropie 10"),
                TestData("Їжакевич", "Yizhakеvych"),
                TestData("Мар'їне", "Marinе"),
                TestData("Йосипівка", "Yosypivka"),
                TestData("Стрий", "Stryi"),
                TestData("Юрій", "Yurii"),
                TestData("Корюківка", "Koriukivka"),
                TestData("Яготин", "Yahotyn"),
                TestData("Костянтин", "Kostiantyn"),
                TestData("Розгон", "Rozghon"),
                TestData("", "")
        )
    }
}
