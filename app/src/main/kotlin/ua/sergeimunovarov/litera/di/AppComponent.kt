/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import dagger.Component
import ua.sergeimunovarov.litera.Events
import ua.sergeimunovarov.litera.StringProvider
import ua.sergeimunovarov.litera.db.ItemDAO
import ua.sergeimunovarov.litera.prefs.Prefs
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DbModule::class])
interface AppComponent {

    fun prefs(): Prefs

    fun itemDAO(): ItemDAO

    fun events(): Events

    fun stringProvider(): StringProvider
}
