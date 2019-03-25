/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit.translator

/**
 *
 * @author Sergei Munovarov
 */
interface Translator {

    /**
     * Translates given string to latin symbols.
     * @param text Text for translation
     * @return Translated text
     */
    fun replaceCharacters(text: String): String
}
