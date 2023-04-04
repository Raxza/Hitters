package com.raxza.hitters.data


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HittersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMenu(menu: Menu): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSet(set: Sets): Long

    @Query("SELECT * FROM menu ORDER BY menuId ASC")
    fun getMenu(): LiveData<List<Menu>>

    @Query("SELECT * FROM sets WHERE menuSetId = :menuSetId")
    fun getSet(menuSetId: Int): LiveData<List<Sets>>

    @Transaction
    @Query("SELECT * FROM menu")
    fun getAllMenuAndSet(): LiveData<List<MenuAndSet>>
}