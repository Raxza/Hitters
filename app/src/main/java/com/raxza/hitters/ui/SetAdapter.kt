package com.raxza.hitters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raxza.hitters.data.Sets
import com.raxza.hitters.databinding.ItemPreWolistBinding

class SetAdapter(private val sets: List<Sets>): RecyclerView.Adapter<SetAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemPreWolistBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(set: Sets) {
            binding.apply {
                tvWorkout.text = set.name
                tvSet.text = set.set
                tvRep.text = set.rep
                tvWeight.text = set.weight
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPreWolistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val set = sets[position]
        holder.bind(set)
    }

    override fun getItemCount(): Int = sets.size
}

