package com.raxza.hitters.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raxza.hitters.R
import com.raxza.hitters.data.Menu
import com.raxza.hitters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpAdapter()
    }

    private fun getMenus(menus: List<Menu>) {
        if (menus.isNotEmpty()) {
            binding.tvNoMenu.visibility = View.GONE
            val menuAdapter = MainAdapter(menus)
            binding.rvMenu.adapter = menuAdapter
        } else binding.tvNoMenu.visibility = View.VISIBLE
    }

    private fun setUpAdapter() {
        binding.rvMenu.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
        viewModel.getMenu().observe(this) { menus ->
            getMenus(menus)
        }
    }

    private fun setUpViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun addNewMenuDialog() {

        val inputEditTextField = EditText(this)
        inputEditTextField.setSingleLine()

        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_menu_title)
            .setMessage(R.string.dialog_menu_desc)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setView(inputEditTextField, 50, 0, 50, 0)

        dialog.setOnShowListener {
            val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                if (inputEditTextField.text.isNotEmpty()) {
                    val editTextInput = inputEditTextField.text.toString()
                    viewModel.newMenu(editTextInput)
                    dialog.dismiss()
                } else {
                    inputEditTextField.error = "Please Input Menu's Name"
                }
            }
        }

        dialog.show()
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
                addNewMenuDialog()
                true
            }
            else -> false
        }
    }
}
