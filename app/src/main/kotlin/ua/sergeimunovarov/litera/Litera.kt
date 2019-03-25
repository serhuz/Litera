/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.app.Application
import com.google.android.gms.ads.MobileAds
import ua.sergeimunovarov.litera.di.*
import ua.sergeimunovarov.litera.main.MainActivity
import ua.sergeimunovarov.litera.translit.TransliterationActivity

open class Litera : Application() {

    lateinit var appComponent: AppComponent

    private var mainComponent: MainComponent? = null
    private var translitComponent: TranslitComponent? = null

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this, "ca-app-pub-6718743335584538~6078046605")
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    fun initMainComponent(activity: MainActivity): MainComponent =
            mainComponent ?: DaggerMainComponent
                    .builder()
                    .appComponent(appComponent)
                    .mainModule(MainModule(activity))
                    .build()
                    .also { mainComponent = it }

    fun releaseMainComponent() {
        mainComponent = null
    }

    fun initTranslitComponent(activity: TransliterationActivity): TranslitComponent =
            translitComponent ?: DaggerTranslitComponent
                    .builder()
                    .appComponent(appComponent)
                    .translitModule(TranslitModule(activity))
                    .build()
                    .also { translitComponent = it }

    fun releaseTranslitComponent() {
        translitComponent = null
    }
}
