/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.util

import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

fun AdView.applyVisibilityAdListener() {
    adListener = object : AdListener() {
        override fun onAdFailedToLoad(errorCode: Int) {
            visibility = View.GONE
        }

        override fun onAdLoaded() {
            visibility = View.VISIBLE
        }
    }
}

fun AdView.loadAdWithDefaultRequest() {
    val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()
    loadAd(adRequest)
}
