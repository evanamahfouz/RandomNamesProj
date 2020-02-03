package com.example.randomnamesproj.ui.main.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randomnamesproj.R
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.databinding.ListQuickBinding

class RandomNameAdapter(private val onItemClicked: (RandomName) -> Unit) :
    ListAdapter<RandomName, RandomNameAdapter.MyViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListQuickBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.list_quick, parent, false
        )
        return MyViewHolder(binding).apply {
            itemView.setOnClickListener {
                onItemClicked(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.v("inBind", getItem(position).name)

        holder.bind(getItem(position))
    }


    class MyViewHolder(private val binding: ListQuickBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RandomName) {
            binding.listModel = data
            if (data.gender == "female") {
                binding.icon.setImageResource(R.drawable.family_daughter)
            } else {
                binding.icon.setImageResource(R.drawable.family_son)

            }

        }

    }

    class DiffCallback : DiffUtil.ItemCallback<RandomName>() {
        override fun areItemsTheSame(oldItem: RandomName, newItem: RandomName): Boolean {

            // check if id is the same
            return oldItem.surname == newItem.surname
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RandomName, newItem: RandomName): Boolean {
            // check if content is the same
            // equals using data class
            return oldItem == newItem
        }
    }
}