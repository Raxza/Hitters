package com.raxza.hitters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raxza.hitters.data.HittersRepository
import com.raxza.hitters.data.Sets
import kotlinx.coroutines.launch

class DetailViewModel(private val hittersRepository: HittersRepository): ViewModel() {

    fun getSets(menuSetId: Int): LiveData<List<Sets>> = hittersRepository.getSets(menuSetId)

    fun newSets(menuSetId: Int, name: String, weight: String, rep: String, set: String) {
        viewModelScope.launch {
            val newSet = Sets(menuSetId = menuSetId, name = name, weight = weight, rep = rep, set = set)
            hittersRepository.addSets(newSet)
        }
    }
}