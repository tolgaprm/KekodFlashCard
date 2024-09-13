package com.prmto.kekodflashcard.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.WordItemBinding
import com.prmto.kekodflashcard.domain.model.WordUI

class WordViewHolder(
    private val binding: WordItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: WordUI,
        onItemClick: (WordUI) -> Unit,
        onFavoriteClick: (WordUI) -> Unit
    ) {
        binding.apply {
            tvMainWord.text = item.englishWord
            tvTurkishMean.text = item.turkishMean
            tvFrenchMean.text = item.frenchMean

            ivWordImage.load(item.imageUrl) {
                crossfade(true)
            }

            root.setOnClickListener {
                onItemClick(item)
            }

            ivFavoriteButton.setOnClickListener {
                onFavoriteClick(item)
            }

            val favoriteRes = if (item.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_unfavorite
            }

            ivFavoriteButton.setImageResource(favoriteRes)
        }
    }

    companion object {
        fun create(parent: ViewGroup): WordViewHolder {
            val binding = WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return WordViewHolder(binding)
        }
    }
}