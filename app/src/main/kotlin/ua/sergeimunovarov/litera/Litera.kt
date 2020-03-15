/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ua.sergeimunovarov.litera.db.ItemDatabase
import ua.sergeimunovarov.litera.main.MainActivityViewModel
import ua.sergeimunovarov.litera.prefs.Prefs
import ua.sergeimunovarov.litera.prefs.PrefsImpl
import ua.sergeimunovarov.litera.translit.TransliterationActivityViewModel
import ua.sergeimunovarov.litera.translit.translator.TranslatorFactory
import ua.sergeimunovarov.litera.translit.translator.TranslatorFactoryImpl

@FlowPreview
@ExperimentalCoroutinesApi
open class Litera : Application() {

    private val appModule = module {
        single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
        single<Prefs> { PrefsImpl(get()) }
        single { FirebaseAnalytics.getInstance(androidContext()) }
        single<Events> { EventsImpl(get()) }
        single { StringProvider(androidContext()) }
    }

    private val dbModule = module {
        single { Room.databaseBuilder(androidContext(), ItemDatabase::class.java, BuildConfig.DB_NAME).build() }
        single { get<ItemDatabase>().itemDao() }
    }

    private val mainModule = module {
        viewModel { MainActivityViewModel(get(), get(), get()) }
    }

    private val translitModule = module {
        factory<TranslatorFactory> { TranslatorFactoryImpl(get()) }
        viewModel { (state: SavedStateHandle) ->
            TransliterationActivityViewModel(state, get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this, "ca-app-pub-6718743335584538~6078046605")
        startKoin {
            androidLogger()
            androidContext(this@Litera)
            modules(appModule, dbModule, mainModule, translitModule)
        }
    }
}
