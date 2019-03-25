/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator.rus

import ua.sergeimunovarov.litera.translit.translator.Translator
import ua.sergeimunovarov.litera.translit.translator.replace
import java.util.*

class TranslatorGostR525351 : Translator {

    override fun replaceCharacters(text: String): String = text.replace(replacementMap)

    companion object {

        @JvmStatic
        private val replacementMap = HashMap<Char, String>()

        init {
            replacementMap['А'] = "A"
            replacementMap['Б'] = "B"
            replacementMap['В'] = "V"
            replacementMap['Г'] = "G"
            replacementMap['Д'] = "D"
            replacementMap['Е'] = "E"
            replacementMap['Ё'] = "E"
            replacementMap['Ж'] = "ZH"
            replacementMap['З'] = "Z"
            replacementMap['И'] = "I"
            replacementMap['Й'] = "J"
            replacementMap['К'] = "K"
            replacementMap['Л'] = "L"
            replacementMap['М'] = "M"
            replacementMap['Н'] = "N"
            replacementMap['О'] = "O"
            replacementMap['П'] = "P"
            replacementMap['Р'] = "R"
            replacementMap['С'] = "S"
            replacementMap['Т'] = "T"
            replacementMap['У'] = "U"
            replacementMap['Ф'] = "F"
            replacementMap['Х'] = "KH"
            replacementMap['Ц'] = "TC"
            replacementMap['Ч'] = "CH"
            replacementMap['Ш'] = "SH"
            replacementMap['Щ'] = "SHCH"
            replacementMap['Ъ'] = ""
            replacementMap['Ы'] = "Y"
            replacementMap['Ь'] = ""
            replacementMap['Э'] = "E"
            replacementMap['Ю'] = "IU"
            replacementMap['Я'] = "IA"
            replacementMap['а'] = "A"
            replacementMap['б'] = "B"
            replacementMap['в'] = "V"
            replacementMap['г'] = "G"
            replacementMap['д'] = "D"
            replacementMap['е'] = "E"
            replacementMap['ё'] = "E"
            replacementMap['ж'] = "ZH"
            replacementMap['з'] = "Z"
            replacementMap['и'] = "I"
            replacementMap['й'] = "J"
            replacementMap['к'] = "K"
            replacementMap['л'] = "L"
            replacementMap['м'] = "M"
            replacementMap['н'] = "N"
            replacementMap['о'] = "O"
            replacementMap['п'] = "P"
            replacementMap['р'] = "R"
            replacementMap['с'] = "S"
            replacementMap['т'] = "T"
            replacementMap['у'] = "U"
            replacementMap['ф'] = "F"
            replacementMap['х'] = "KH"
            replacementMap['ц'] = "TC"
            replacementMap['ч'] = "CH"
            replacementMap['ш'] = "SH"
            replacementMap['щ'] = "SHCH"
            replacementMap['ъ'] = ""
            replacementMap['ы'] = "Y"
            replacementMap['ь'] = ""
            replacementMap['э'] = "E"
            replacementMap['ю'] = "IU"
            replacementMap['я'] = "IA"
        }
    }
}
