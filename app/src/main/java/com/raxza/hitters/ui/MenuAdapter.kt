package com.raxza.hitters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raxza.hitters.data.Menu
import com.raxza.hitters.databinding.ItemMenuBinding

class MainAdapter(private val menu: List<Menu>): RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = menu[position]
        holder.binding.apply {
            tvDay.text = menu.name
        }
    }

    override fun getItemCount(): Int = menu.size

}