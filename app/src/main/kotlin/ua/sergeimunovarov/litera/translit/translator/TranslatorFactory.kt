/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator

import ua.sergeimunovarov.litera.db.Standard
import ua.sergeimunovarov.litera.prefs.Prefs
import ua.sergeimunovarov.litera.translit.translator.rus.TranslatorGost779
import ua.sergeimunovarov.litera.translit.translator.rus.TranslatorGostR525351
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorGost779Ukr
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorKmu10
import ua.sergeimunovarov.litera.translit.translator.ukr.TranslatorUsbgnUkr

interface TranslatorFactory {
    fun createTranslator(): Translator
}

class TranslatorFactoryImpl(private val prefs: Prefs) : TranslatorFactory {

    override fun createTranslator(): Translator =
            when (prefs.setting.standard) {
                Standard.KMU_10 -> TranslatorKmu10()
                Standard.GOST_779_UK -> TranslatorGost779Ukr()
                Standard.USBGN -> TranslatorUsbgnUkr()
                Standard.GOST_R_52535 -> TranslatorGostR525351()
                Standard.GOST_779_RU -> TranslatorGost779()
            }
}
