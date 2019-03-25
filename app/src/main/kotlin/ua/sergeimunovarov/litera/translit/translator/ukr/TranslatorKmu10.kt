/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator.ukr

import ua.sergeimunovarov.litera.translit.translator.Translator
import ua.sergeimunovarov.litera.translit.translator.correct
import ua.sergeimunovarov.litera.translit.translator.replace
import java.util.*

/**
 * Implements ITranslator interface. Transliterates original according to
 * standard accepted by Cabinet of Ministers of Ukraine.
 *
 * @author Sergei Munovarov
 * @see <a href="http://zakon3.rada.gov.ua/laws/show/55-2010-%D0%BF">Document</a>
 */
class TranslatorKmu10 : Translator {

    override fun replaceCharacters(text: String): String =
            text.trim()
                    .split("\\s+".toRegex())
                    .fold(ArrayList()) { list: ArrayList<String>, word: String ->
                        list.apply {
                            val result = word.correct(correctionMap).run {
                                this.replaceFirst(replacementMapSpecial).replace(replacementMap)
                            }
                            add(result)
                        }
                    }.fold(StringBuilder()) { builder: StringBuilder, word: String ->
                        builder.append("$word ")
                    }.toString().trimEnd()

    companion object {

        @JvmStatic
        private val replacementMap = HashMap<Char, String>()

        /**
         * Map for some cyrillic (Ukrainian) symbols which should be transliterated
         * according to special rules if they are found at the beginning of the word.
         */
        @JvmStatic
        private val replacementMapSpecial = HashMap<Char, String>()

        @JvmStatic
        private val correctionMap = HashMap<String, String>()

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
            replacementMap['Е'] = "E"
            replacementMap['е'] = "е"
            replacementMap['Є'] = "Ie"
            replacementMap['є'] = "ie"
            replacementMap['Ж'] = "Zh"
            replacementMap['ж'] = "zh"
            replacementMap['З'] = "Z"
            replacementMap['з'] = "z"
            replacementMap['И'] = "Y"
            replacementMap['и'] = "y"
            replacementMap['І'] = "I"
            replacementMap['і'] = "i"
            replacementMap['Ї'] = "I"
            replacementMap['ї'] = "i"
            replacementMap['Й'] = "Y"
            replacementMap['й'] = "i"
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
            replacementMap['ю'] = "iu"
            replacementMap['Я'] = "Ia"
            replacementMap['я'] = "ia"
            replacementMap['\''] = ""
            replacementMap['Ь'] = ""
            replacementMap['ь'] = ""

            replacementMapSpecial['Є'] = "Ye"
            replacementMapSpecial['є'] = "ye"
            replacementMapSpecial['Ї'] = "Yi"
            replacementMapSpecial['ї'] = "yi"
            replacementMapSpecial['Ю'] = "Yu"
            replacementMapSpecial['ю'] = "yu"
            replacementMapSpecial['Я'] = "Ya"
            replacementMapSpecial['я'] = "ya"

            correctionMap["ЗГ"] = "ZGH"
            correctionMap["Зг"] = "Zgh"
            correctionMap["зг"] = "zgh"
            correctionMap["зГ"] = "zGH"
        }
    }
}

fun String.replaceFirst(replacementMap: Map<Char, String>): String =
        replacementMap
                .entries
                .fold(this) { acc: String, entry: Map.Entry<Char, String> ->
                    if (acc.startsWith(entry.key)) {
                        acc.replaceFirst(entry.key.toString(), entry.value, false)
                    } else {
                        acc
                    }
                }
