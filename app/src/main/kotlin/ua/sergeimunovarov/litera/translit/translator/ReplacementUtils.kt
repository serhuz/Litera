/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator

fun String.replace(replacementMap: Map<Char, String>): String =
        this.fold(StringBuilder()) { builder: StringBuilder, char: Char ->
            builder.apply {
                replacementMap[char]?.let {
                    append(it)
                } ?: run {
                    append(char)
                }
            }
        }.toString()

fun String.correct(correctionMap: Map<String, String>): String =
        correctionMap.entries.fold(this) { acc: String, entry: Map.Entry<String, String> ->
            if (acc.contains(entry.key)) {
                acc.replace(entry.key, entry.value, false)
            } else {
                acc
            }
        }
