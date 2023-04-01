package com.raxza.hitters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raxza.hitters.data.Menu
import com.raxza.hitters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpAdapater()

        mainViewModel.menu.observe(this) { menus ->
            getMenus(menus)
        }
    }

    private fun getMenus(menus: List<Menu>) {
        val menuAdapter = MainAdapter(menus)
        binding.rvMenu.adapter = menuAdapter
    }

    private fun setUpAdapater() {
        binding.rvMenu.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun setUpViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        mainViewModel.getMenu()
    }
}
