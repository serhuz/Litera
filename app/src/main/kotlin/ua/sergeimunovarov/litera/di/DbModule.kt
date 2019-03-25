/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ua.sergeimunovarov.litera.BuildConfig
import ua.sergeimunovarov.litera.db.ItemDAO
import ua.sergeimunovarov.litera.db.ItemDatabase
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ItemDatabase =
            Room.databaseBuilder(context, ItemDatabase::class.java, BuildConfig.DB_NAME).build()

    @Provides
    @Singleton
    fun provideItemDAO(database: ItemDatabase): ItemDAO = database.itemDao()
}
