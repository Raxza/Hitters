package com.raxza.hitters.data


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HittersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenu(menu: Menu)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSet(set: Sets)

    @Query("SELECT * FROM menu ORDER BY name ASC")
    fun getMenu(): LiveData<List<Menu>>

    @Query("SELECT * FROM sets WHERE menuSetId = :menuSetId")
    fun getSet(menuSetId: String): LiveData<List<Sets>>

    @Transaction
    @Query("SELECT * FROM sets")
    fun getAllMenuAndSet(): LiveData<List<MenuAndSet>>
}