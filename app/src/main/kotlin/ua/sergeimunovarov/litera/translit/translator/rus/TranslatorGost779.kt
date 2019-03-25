/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator.rus

import ua.sergeimunovarov.litera.translit.translator.Translator
import ua.sergeimunovarov.litera.translit.translator.correct
import ua.sergeimunovarov.litera.translit.translator.replace
import java.util.*

class TranslatorGost779 : Translator {

    override fun replaceCharacters(text: String): String = text.replace(replacementMap).correct(correctionMap)

    companion object {

        @JvmStatic
        private val replacementMap = HashMap<Char, String>()

        @JvmStatic
        private val correctionMap = HashMap<String, String>()

        init {
            replacementMap['А'] = "A"
            replacementMap['а'] = "a"
            replacementMap['Б'] = "B"
            replacementMap['б'] = "b"
            replacementMap['В'] = "V"
            replacementMap['в'] = "v"
            replacementMap['Г'] = "G"
            replacementMap['г'] = "g"
            replacementMap['Д'] = "D"
            replacementMap['д'] = "d"
            replacementMap['Е'] = "E"
            replacementMap['е'] = "e"
            replacementMap['Ё'] = "Yo"
            replacementMap['ё'] = "yo"
            replacementMap['Ж'] = "Zh"
            replacementMap['ж'] = "zh"
            replacementMap['З'] = "Z"
            replacementMap['з'] = "z"
            replacementMap['И'] = "I"
            replacementMap['и'] = "i"
            replacementMap['Й'] = "J"
            replacementMap['й'] = "j"
            replacementMap['К'] = "K"
            replacementMap['к'] = "k"
            replacementMap['Л'] = "L"
            replacementMap['л'] = "l"
            replacementMap['М'] = "M"
            replacementMap['м'] = "m"
            replacementMap['Н'] = "N"
            replacementMap['н'] = "n"
            replacementMap['О'] = "O"
            replacementMap['о'] = "o"
            replacementMap['П'] = "P"
            replacementMap['п'] = "p"
            replacementMap['Р'] = "R"
            replacementMap['р'] = "r"
            replacementMap['С'] = "S"
            replacementMap['с'] = "s"
            replacementMap['Т'] = "T"
            replacementMap['т'] = "t"
            replacementMap['У'] = "U"
            replacementMap['у'] = "u"
            replacementMap['Ф'] = "F"
            replacementMap['ф'] = "f"
            replacementMap['Х'] = "X"
            replacementMap['х'] = "x"
            replacementMap['Ц'] = "Cz"
            replacementMap['ц'] = "cz"
            replacementMap['Ч'] = "Ch"
            replacementMap['ч'] = "ch"
            replacementMap['Ш'] = "Sh"
            replacementMap['ш'] = "sh"
            replacementMap['Щ'] = "Shh"
            replacementMap['щ'] = "shh"
            replacementMap['Ъ'] = "``"
            replacementMap['ъ'] = "``"
            replacementMap['Ы'] = "Y`"
            replacementMap['ы'] = "y`"
            replacementMap['Ь'] = "`"
            replacementMap['ь'] = "`"
            replacementMap['Э'] = "E`"
            replacementMap['э'] = "e`"
            replacementMap['Ю'] = "Yu"
            replacementMap['ю'] = "yu"
            replacementMap['Я'] = "Ya"
            replacementMap['я'] = "ya"

            correctionMap["CzI"] = "CI"
            correctionMap["Czi"] = "Ci"
            correctionMap["czI"] = "cI"
            correctionMap["czi"] = "ci"
            correctionMap["CzE"] = "CE"
            correctionMap["Cze"] = "Ce"
            correctionMap["czE"] = "cE"
            correctionMap["cze"] = "ce"
            correctionMap["CzY"] = "CY"
            correctionMap["Czy"] = "Cy"
            correctionMap["czY"] = "cY"
            correctionMap["czy"] = "cy"
            correctionMap["CzJ"] = "CJ"
            correctionMap["Czj"] = "Cj"
            correctionMap["czJ"] = "cJ"
            correctionMap["czj"] = "cj"
        }
    }
}
