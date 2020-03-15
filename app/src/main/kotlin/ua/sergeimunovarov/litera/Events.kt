/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

interface Events {

    fun logVoiceRecognition(hasRecognition: Boolean)

    fun logCameraRecognition()

    fun logTextCopied()
}

class EventsImpl(private val analytics: FirebaseAnalytics) : Events {

    override fun logVoiceRecognition(hasRecognition: Boolean) {
        Bundle().apply { putBoolean("voice_recognition_present", hasRecognition) }
                .let { analytics.logEvent("voice_recognition", it) }
    }

    override fun logCameraRecognition() {
        analytics.logEvent("camera_recognition", null)
    }

    override fun logTextCopied() {
        analytics.logEvent("text_copied", null)
    }
}
