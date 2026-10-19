package com.smartx.rfidreader.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartx.rfidreader.R
import com.smartx.rfidreader.databinding.ItemHomeEpcBinding

data class HomeEpcGroupItem(
    val description: String,
    val count: Int,
    val epcs: List<String>
)

class HomeEpcAdapter(
    private val onInfoClick: (HomeEpcGroupItem) -> Unit
) : ListAdapter<HomeEpcGroupItem, HomeEpcAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemHomeEpcBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeEpcGroupItem) {
            binding.textDescription.text = item.description
            binding.textCount.text = binding.root.context.getString(
                R.string.home_registered_group_count,
                item.count
            )
            binding.btnGroupInfo.setOnClickListener { onInfoClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeEpcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<HomeEpcGroupItem>() {
        override fun areItemsTheSame(oldItem: HomeEpcGroupItem, newItem: HomeEpcGroupItem): Boolean =
            oldItem.description == newItem.description

        override fun areContentsTheSame(oldItem: HomeEpcGroupItem, newItem: HomeEpcGroupItem): Boolean =
            oldItem == newItem
    }
}
