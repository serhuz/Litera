/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ItemDAO {

    @Insert(onConflict = REPLACE)
    fun insert(item: Item)

    @Query("DELETE FROM items WHERE id = :id")
    fun deleteItemById(id: Long)

    @Query("DELETE FROM items")
    fun deleteAll()

    @Query("SELECT * FROM items ORDER BY ts DESC")
    fun itemsByTimestamp(): DataSource.Factory<Int, Item>
}
