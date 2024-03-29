package com.raxza.hitters.data

import androidx.lifecycle.LiveData
class HittersRepository(private val database: HittersDatabase) {

    fun getMenu(): LiveData<List<Menu>> {
        return database.hittersDao().getMenu()
    }

    fun getSets(menuSetId: Int): LiveData<List<Sets>> {
        return database.hittersDao().getSet(menuSetId)
    }

    suspend fun addMenu(name: String) {
        val newMenu = Menu(name = name)
        database.hittersDao().insertMenu(newMenu)
    }

    suspend fun addSets(set: Sets) {
        database.hittersDao().insertSet(set)
    }

    companion object {

        @Volatile
        private var instance: HittersRepository? = null
        fun getInstance(
            database: HittersDatabase
        ): HittersRepository =
            instance ?: synchronized(this) {
                instance ?: HittersRepository(database)
            }.also { instance= it }
    }
}