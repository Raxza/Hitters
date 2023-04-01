package com.raxza.hitters.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raxza.hitters.R
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

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                true
            }
            R.id.action_add -> {
                val inputEditTextField = EditText(this)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Add new menu")
                    .setMessage("Input menu name")
                    .setView(inputEditTextField)
                    .setPositiveButton("Add") { _, _ ->
                        val editTextInput = inputEditTextField.text.toString()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                dialog.show()
                true
            }
            else -> false
        }
    }
}
