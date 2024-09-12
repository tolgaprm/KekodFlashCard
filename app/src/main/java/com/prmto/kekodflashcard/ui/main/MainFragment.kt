package com.prmto.kekodflashcard.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.prmto.kekodflashcard.common.collectFlow
import com.prmto.kekodflashcard.data.remote.response.WordResponse
import com.prmto.kekodflashcard.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private val wordAdapter by lazy {
        WordAdapter(
            onItemClick = ::onItemClicked,
            onListenClick = ::onListenClicked
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWords()
        binding.rvWords.adapter = wordAdapter
        collectFlow(viewModel.categoryItems, ::setCategoryItems)
        collectFlow(viewModel.uiState, ::setUiState)
    }

    private fun setUiState(uiState: MainUiState) {
        wordAdapter.submitList(uiState.words)
    }

    private fun setCategoryItems(categoryItems: List<CategoryItem>) {
        binding.categoryItemFavorite.apply {
            ivCategoryIcon.setImageResource(categoryItems[0].iconRes)
            tvCategoryTitle.text = categoryItems[0].title.asString(requireContext())
            tvCategorySubtitle.text = categoryItems[0].subtitle.asString(requireContext())
        }

        binding.categoryItemAI.apply {
            ivCategoryIcon.setImageResource(categoryItems[1].iconRes)
            tvCategoryTitle.text = categoryItems[1].title.asString(requireContext())
            tvCategorySubtitle.text = categoryItems[1].subtitle.asString(requireContext())
        }
    }

    private fun onItemClicked(item: WordResponse) {

    }

    private fun onListenClicked(englishWord: String) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}