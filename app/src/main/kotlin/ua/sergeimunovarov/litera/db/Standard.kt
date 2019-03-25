/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.db

import androidx.annotation.StringRes
import androidx.room.TypeConverter
import ua.sergeimunovarov.litera.R

enum class Lang {
    RU, UK
}

enum class Standard(val lang: Lang, @StringRes val displayName: Int) {
    // Ukrainian
    KMU_10(Lang.UK, R.string.mode_ukr_kmu10),
    USBGN(Lang.UK, R.string.mode_ukr_usbgn),
    GOST_779_UK(Lang.UK, R.string.mode_ukr_gost779),

    // Russian
    GOST_R_52535(Lang.RU, R.string.mode_rus_gostr52535),
    GOST_779_RU(Lang.RU, R.string.mode_ukr_gost779)
}

object Converters {

    @JvmStatic
    @TypeConverter
    fun fromLang(lang: Lang?) = lang?.name

    @JvmStatic
    @TypeConverter
    fun toLang(value: String?) = value?.let { Lang.valueOf(it) }

    @JvmStatic
    @TypeConverter
    fun fromStandard(standard: Standard?) = standard?.name

    @JvmStatic
    @TypeConverter
    fun toStandard(value: String?) = value?.let { Standard.valueOf(it) }
}

fun getDistinctLanguages() = Standard.values().distinctBy { it.lang }.map { it.lang }.asReversed()

fun getStandardsForLanguage(lang: Lang) = Standard.values().filter { it.lang == lang }
