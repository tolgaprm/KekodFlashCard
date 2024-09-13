package com.prmto.kekodflashcard.ui.learned

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.collectFlow
import com.prmto.kekodflashcard.databinding.FragmentMyLearnedBinding
import com.prmto.kekodflashcard.domain.model.WordUI
import com.prmto.kekodflashcard.ui.adapter.WordAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyLearnedFragment : Fragment(R.layout.fragment_my_learned) {

    private var _binding: FragmentMyLearnedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyLearnedViewModel by viewModels()

    private val wordAdapter by lazy {
        WordAdapter(
            onItemClick = ::onItemClicked,
            onFavoriteClick = viewModel::onFavoriteClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyLearnedBinding.bind(view)
        viewModel.getLearnedWords()
        collectFlow(viewModel.uiState, ::setUiState)
        binding.rvWords.adapter = wordAdapter
        setEditTextListener()
    }

    private fun setEditTextListener() {
        binding.etSearchWord.doOnTextChanged { text, _, _, _ ->
            viewModel.searchWord(text.toString())
        }
    }


    private fun setUiState(uiState: MyLearnedUiState) {
        binding.progressBar.isVisible = uiState.isLoading
        binding.rvWords.isVisible = !uiState.isLoading
        wordAdapter.submitList(uiState.learnedWords)
    }

    private fun onItemClicked(item: WordUI) {
        val action =
            MyLearnedFragmentDirections.actionMyLearnedFragmentToDetailDialogFragment(
                wordUiItem = item,
                isFromLearnedFragment = true
            )

        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}