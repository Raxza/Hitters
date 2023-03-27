package com.raxza.hitters.data

import androidx.lifecycle.LiveData
class HittersRepository(private val database: HittersDatabase) {

    fun getMenus(): LiveData<List<Menu>> {
        return database.hittersDao().getMenu()
    }

    fun getSets(menuSetId: String): LiveData<List<Set>> {
        return database.hittersDao().getSet(menuSetId)
    }

}