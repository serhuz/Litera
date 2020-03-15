/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ua.sergeimunovarov.litera.db.Standard
import ua.sergeimunovarov.litera.prefs.Prefs
import ua.sergeimunovarov.litera.prefs.Setting
import ua.sergeimunovarov.litera.translit.translator.rus.TranslatorGost779
import ua.sergeimunovarov.litera.translit.translator.rus.TranslatorGostR525351
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorGost779Ukr
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorKmu10
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorUsbgnUkr

@RunWith(Parameterized::class)
class TranslatorFactoryTest {

    @Parameterized.Parameter(0)
    lateinit var given: Prefs

    @Parameterized.Parameter(1)
    lateinit var expected: Class<Translator>

    lateinit var translatorFactory: TranslatorFactory

    @Test
    fun createTranslator() {
        translatorFactory = TranslatorFactoryImpl(given)

        val actual = translatorFactory.createTranslator()

        assertThat(actual).isInstanceOf(expected)
    }

    companion object {

        @Parameterized.Parameters(name = "{index}: create {1}")
        @JvmStatic
        fun getData() = arrayOf(
                arrayOf(
                        object : Prefs {
                            override var setting: Setting
                                get() = Setting(Standard.GOST_779_RU)
                                set(value) {}
                        },
                        TranslatorGost779::class.java),
                arrayOf(
                        object : Prefs {
                            override var setting: Setting
                                get() = Setting(Standard.GOST_779_UK)
                                set(value) {}
                        },
                        TranslatorGost779Ukr::class.java
                ),
                arrayOf(
                        object : Prefs {
                            override var setting: Setting
                                get() = Setting(Standard.GOST_R_52535)
                                set(value) {}
                        },
                        TranslatorGostR525351::class.java
                ),
                arrayOf(
                        object : Prefs {
                            override var setting: Setting
                                get() = Setting(Standard.KMU_10)
                                set(value) {}
                        },
                        TranslatorKmu10::class.java
                ),
                arrayOf(
                        object : Prefs {
                            override var setting: Setting
                                get() = Setting(Standard.USBGN)
                                set(value) {}
                        },
                        TranslatorUsbgnUkr::class.java
                ))
    }
}
