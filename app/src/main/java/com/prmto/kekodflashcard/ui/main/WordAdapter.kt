package com.prmto.kekodflashcard.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.prmto.kekodflashcard.data.remote.response.WordResponse

class WordAdapter(
    private val onItemClick: (WordResponse) -> Unit,
    private val onListenClick: (String) -> Unit
) : ListAdapter<WordResponse, WordViewHolder>(WordDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item = item,
            onItemClick = onItemClick,
            onListenClick = onListenClick
        )
    }
}


class WordDiffCallback : DiffUtil.ItemCallback<WordResponse>() {
    override fun areItemsTheSame(oldItem: WordResponse, newItem: WordResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordResponse, newItem: WordResponse): Boolean {
        return oldItem == newItem
    }
}