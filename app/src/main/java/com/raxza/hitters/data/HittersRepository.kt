package com.raxza.hitters.data

import androidx.lifecycle.LiveData
class HittersRepository(private val database: HittersDatabase) {

    fun getMenu(): List<Menu> {
        return database.hittersDao().getMenu()
    }

    fun getSets(menuSetId: String): LiveData<List<Sets>> {
        return database.hittersDao().getSet(menuSetId)
    }

    fun addMenu(name: String) {
        val newMenu = Menu(name = name)
        database.hittersDao().insertMenu(newMenu)
    }

    fun addSets(set: Sets) {
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