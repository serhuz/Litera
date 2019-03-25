/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ua.sergeimunovarov.litera.StringProvider
import ua.sergeimunovarov.litera.db.ItemDAO
import ua.sergeimunovarov.litera.di.annotation.MainActivityScope
import ua.sergeimunovarov.litera.main.MainActivity
import ua.sergeimunovarov.litera.main.MainActivityViewModel
import ua.sergeimunovarov.litera.prefs.Prefs

@Module
class MainModule(private val activity: MainActivity) {

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModelFactory(itemDAO: ItemDAO,
                                            prefs: Prefs,
                                            stringProvider: StringProvider) =
            MainActivityViewModel.Factory(itemDAO, prefs, stringProvider)

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModel(factory: MainActivityViewModel.Factory) =
            ViewModelProvider(activity, factory).get(MainActivityViewModel::class.java)
}
