/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.sergeimunovarov.litera.R
import ua.sergeimunovarov.litera.databinding.ViewHistoryItemBinding
import ua.sergeimunovarov.litera.db.Item

class HistoryAdapter : PagedListAdapter<Item, HistoryViewHolder>(DIFF_CALLBACK), HistoryItemClickListener {

    val itemLiveData = MutableLiveData<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            with(LayoutInflater.from(parent.context)) {
                DataBindingUtil.inflate<ViewHistoryItemBinding>(
                        this,
                        R.layout.view_history_item,
                        parent,
                        false
                )
            }.let(::HistoryViewHolder)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(this, getItem(position))
    }

    override fun onItemClicked(item: Item) {
        itemLiveData.value = item
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
        }
    }
}

class HistoryViewHolder(private val binding: ViewHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listener: HistoryItemClickListener, item: Item?) {
        binding.listener = listener
        binding.item = item
    }

    val item: Item
        get() = binding.item!!
}

interface HistoryItemClickListener {

    fun onItemClicked(item: Item)
}
