/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator.ukr

import ua.sergeimunovarov.litera.translit.translator.Translator
import ua.sergeimunovarov.litera.translit.translator.replace
import java.util.*

class TranslatorUsbgnUkr : Translator {

    override fun replaceCharacters(text: String): String = text.replace(replacementMap)

    companion object {

        @JvmStatic
        private val replacementMap = HashMap<Char, String>()

        init {
            replacementMap['А'] = "A"
            replacementMap['а'] = "a"
            replacementMap['Б'] = "B"
            replacementMap['б'] = "b"
            replacementMap['В'] = "V"
            replacementMap['в'] = "v"
            replacementMap['Г'] = "H"
            replacementMap['г'] = "h"
            replacementMap['Ґ'] = "G"
            replacementMap['ґ'] = "g"
            replacementMap['Д'] = "D"
            replacementMap['д'] = "d"
            replacementMap['ґ'] = "g"
            replacementMap['Е'] = "E"
            replacementMap['е'] = "е"
            replacementMap['Є'] = "Ye"
            replacementMap['є'] = "ye"
            replacementMap['Ж'] = "Zh"
            replacementMap['ж'] = "zh"
            replacementMap['З'] = "Z"
            replacementMap['з'] = "z"
            replacementMap['И'] = "Y"
            replacementMap['и'] = "y"
            replacementMap['І'] = "I"
            replacementMap['і'] = "i"
            replacementMap['Ї'] = "Yi"
            replacementMap['ї'] = "yi"
            replacementMap['Й'] = "Y"
            replacementMap['й'] = "y"
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
            replacementMap['Х'] = "Kh"
            replacementMap['х'] = "kh"
            replacementMap['Ц'] = "Ts"
            replacementMap['ц'] = "ts"
            replacementMap['Ч'] = "Ch"
            replacementMap['ч'] = "ch"
            replacementMap['Ш'] = "Sh"
            replacementMap['ш'] = "sh"
            replacementMap['Щ'] = "Shch"
            replacementMap['щ'] = "shch"
            replacementMap['Ю'] = "Yu"
            replacementMap['ю'] = "yu"
            replacementMap['Я'] = "Ya"
            replacementMap['я'] = "ya"
            replacementMap['\''] = "\""
            replacementMap['Ь'] = "\'"
            replacementMap['ь'] = "\'"
        }
    }
}
