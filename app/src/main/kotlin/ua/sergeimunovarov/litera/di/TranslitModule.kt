/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ua.sergeimunovarov.litera.db.ItemDAO
import ua.sergeimunovarov.litera.di.annotation.TransliterationActivityScope
import ua.sergeimunovarov.litera.prefs.Prefs
import ua.sergeimunovarov.litera.translit.TransliterationActivity
import ua.sergeimunovarov.litera.translit.TransliterationActivityViewModel

@Module
class TranslitModule(private val activity: TransliterationActivity) {

    @Provides
    @TransliterationActivityScope
    fun provideTransliterationActivityViewModer(prefs: Prefs, itemDAO: ItemDAO): TransliterationActivityViewModel.Factory =
            TransliterationActivityViewModel.Factory(activity, prefs, itemDAO)

    @Provides
    @TransliterationActivityScope
    fun provideTransliterationActivityViewModel(factory: TransliterationActivityViewModel.Factory) =
            ViewModelProvider(activity, factory).get(TransliterationActivityViewModel::class.java)
}
