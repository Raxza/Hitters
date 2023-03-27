package com.raxza.hitters.data


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HittersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenu(menu: Menu)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSet(set: Set)

    @Query("SELECT * FROM menu ORDER BY name ASC")
    fun getMenu(): LiveData<List<Menu>>

    @Query("SELECT * FROM set WHERE menuSetId = :menuSetId")
    fun getSet(menuSetId: String): LiveData<List<Set>>

    @Transaction
    @Query("SELECT * FROM set")
    fun getAllMenuAndSet(): LiveData<List<MenuAndSet>>
}