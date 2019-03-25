/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class Events(private val analytics: FirebaseAnalytics) {

    fun logVoiceRecognition(hasRecognition: Boolean) {
        Bundle().apply { putBoolean("voice_recognition_present", hasRecognition) }
                .let { analytics.logEvent("voice_recognition", it) }
    }

    fun logCameraRecognition() {
        analytics.logEvent("camera_recognition", null)
    }

    fun logTextCopied() {
        analytics.logEvent("text_copied", null)
    }
}
