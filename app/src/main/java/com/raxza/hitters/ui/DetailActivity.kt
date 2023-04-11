package com.raxza.hitters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raxza.hitters.data.Sets
import com.raxza.hitters.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivAdd.setOnClickListener {
            val dialogFragment = AddSetDialog()
            dialogFragment.show(supportFragmentManager, "addNewSets")
        }

        setUpViewModel()
        setUpAdapter()
    }

    private fun getSets(sets: List<Sets>) {
        if (sets.isNotEmpty()) {
            val setAdapter = SetAdapter(sets)
            binding.rvSets.adapter = setAdapter
        }
    }

    private fun setUpAdapter() {

        binding.rvSets.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            setHasFixedSize(true)
        }
        val menuId = intent.getIntExtra("MenuID", 0)
        viewModel.getSets(menuId).observe(this){ sets ->
            getSets(sets)
        }
    }

    private fun setUpViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }
}