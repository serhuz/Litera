/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.util


import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import ua.sergeimunovarov.litera.db.Lang


object VoiceRecognitionUtil {

    @JvmStatic
    fun voiceRecognitionPresent(manager: PackageManager) =
            manager.queryIntentActivities(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0).size > 0

    @JvmStatic
    fun createRecognizerIntent(packageName: String, lang: Lang) =
            with(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)) {
                putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            }.apply {
                when (lang) {
                    Lang.RU -> putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru-RU")
                    Lang.UK -> putExtra(RecognizerIntent.EXTRA_LANGUAGE, "uk-UA")
                }
            }

}
