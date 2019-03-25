/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [(Item::class)], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDAO
}
