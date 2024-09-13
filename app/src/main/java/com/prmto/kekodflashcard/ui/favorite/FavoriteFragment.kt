package com.prmto.kekodflashcard.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.collectFlow
import com.prmto.kekodflashcard.databinding.FragmentFavoriteBinding
import com.prmto.kekodflashcard.ui.adapter.WordAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()

    private val wordAdapter by lazy {
        WordAdapter(
            onItemClick = { },
            onFavoriteClick = viewModel::onFavoriteClick
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)
        viewModel.getFavoriteWords()
        setClickListeners()
        binding.rvWords.adapter = wordAdapter
        collectFlow(viewModel.uiState) { uiState ->
            binding.progressBar.isVisible = uiState.isLoading
            binding.rvWords.isVisible = !uiState.isLoading
            wordAdapter.submitList(uiState.words)
        }
    }

    private fun setClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}