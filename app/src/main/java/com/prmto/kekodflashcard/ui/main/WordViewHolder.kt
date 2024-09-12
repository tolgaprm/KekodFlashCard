package com.prmto.kekodflashcard.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.databinding.WordItemBinding

class WordViewHolder(
    private val binding: WordItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: WordResponse,
        onItemClick: (WordResponse) -> Unit,
        onListenClick: (String) -> Unit
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

            ivPlayButton.setOnClickListener {
                onListenClick(item.englishWord)
            }
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