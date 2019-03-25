/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "items", indices = [Index("ts")])
data class Item(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                @ColumnInfo(name = "original") var original: String,
                @ColumnInfo(name = "romanized") var romanized: String,
                @ColumnInfo(name = "standard") var standard: Standard,
                @ColumnInfo(name = "ts") var timestamp: Long = System.currentTimeMillis())
