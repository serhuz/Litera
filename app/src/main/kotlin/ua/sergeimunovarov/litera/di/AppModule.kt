/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import ua.sergeimunovarov.litera.Events
import ua.sergeimunovarov.litera.StringProvider
import ua.sergeimunovarov.litera.prefs.Prefs
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context /* specified intentionally */ =
            context.applicationContext

    @Provides
    @Singleton
    fun providePreferences(): SharedPreferences /* specified intentionally */ =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun providePrefs(sharedPreferences: SharedPreferences) = Prefs(sharedPreferences)

    @Provides
    @Singleton
    fun provideAnalytics() = FirebaseAnalytics.getInstance(context)

    @Provides
    @Singleton
    fun provideEvents(analytics: FirebaseAnalytics) = Events(analytics)

    @Provides
    @Singleton
    fun provideStringProvider() = StringProvider(context)
}
