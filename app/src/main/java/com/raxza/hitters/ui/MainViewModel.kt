package com.raxza.hitters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raxza.hitters.data.HittersRepository
import com.raxza.hitters.data.Menu
import com.raxza.hitters.data.Result

class MainViewModel(private val hittersRepository: HittersRepository): ViewModel() {

    fun getMenu(): LiveData<List<Menu>> = hittersRepository.getMenu()

    fun newMenu(name: String) = hittersRepository.addMenu(name)
}