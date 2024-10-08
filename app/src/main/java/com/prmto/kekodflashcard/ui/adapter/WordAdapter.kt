package com.prmto.kekodflashcard.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.prmto.kekodflashcard.domain.model.WordUI

class WordAdapter(
    private val onItemClick: (WordUI) -> Unit,
    private val onFavoriteClick: (WordUI) -> Unit
) : ListAdapter<WordUI, WordViewHolder>(WordDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item = item,
            onItemClick = onItemClick,
            onFavoriteClick = onFavoriteClick
        )
    }
}


class WordDiffCallback : DiffUtil.ItemCallback<WordUI>() {
    override fun areItemsTheSame(oldItem: WordUI, newItem: WordUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordUI, newItem: WordUI): Boolean {
        return oldItem == newItem
    }
}