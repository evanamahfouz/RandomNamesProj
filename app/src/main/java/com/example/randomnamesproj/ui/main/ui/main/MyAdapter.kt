package com.example.randomnamesproj.ui.main.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randomnamesproj.R
import com.example.randomnamesproj.data.model.Example
import com.example.randomnamesproj.databinding.ListQuickBinding

class MyAdapter(private val context: Context) :
    ListAdapter<Example, MyAdapter.MyViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListQuickBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context)
            , R.layout.list_quick, parent, false
        )
        return MyViewHolder(binding).apply {
            //            itemView.setOnClickListener {
//                val context = itemView.context
//                val volumeInfo = getItem(adapterPosition)
//                val intent = Intent(context, BookDescriptionActivity::class.java)
//                    .putExtra(BookDescriptionActivity.ARG_DESC, volumeInfo.description)
//                context.startActivity(intent)
//            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.v("inBind", getItem(position).name)

        holder.bind(getItem(position))
    }

    /**
     * View holder class
     */

    class MyViewHolder(private val binding: ListQuickBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Example) {
            binding.listModel = data
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Example>() {
        override fun areItemsTheSame(oldItem: Example, newItem: Example): Boolean {

            // check if id is the same
            return oldItem.surname.equals(newItem.surname)
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Example, newItem: Example): Boolean {
            // check if content is the same
            // equals using data class
            return oldItem == newItem
        }
    }
}