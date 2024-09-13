package com.prmto.kekodflashcard.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.collectEvent
import com.prmto.kekodflashcard.common.collectFlow
import com.prmto.kekodflashcard.databinding.FragmentMainBinding
import com.prmto.kekodflashcard.domain.model.WordUI
import com.prmto.kekodflashcard.ui.adapter.WordAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private val wordAdapter by lazy {
        WordAdapter(
            onItemClick = ::onItemClicked,
            onFavoriteClick = viewModel::onFavoriteClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        viewModel.getWords(isShuffle = true)
        setEditListener()
        binding.rvWords.apply {
            adapter = wordAdapter
            itemAnimator = null
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWords()
            binding.etSearchWord.text?.clear()
        }
        setClickListeners()
        collectFlow(viewModel.categoryItems, ::setCategoryItems)
        collectFlow(viewModel.uiState, ::setUiState)
        collectEvent(viewModel.viewEvent, ::handleViewEvent)
    }

    private fun setClickListeners() {
        binding.categoryItemFavorite.root.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToFavoriteFragment()
            )
        }
    }

    private fun setUiState(uiState: MainUiState) {
        binding.swipeRefreshLayout.isRefreshing = uiState.loading
        binding.rvWords.isVisible = !uiState.loading
        binding.progressBar.isVisible = uiState.loading
        wordAdapter.submitList(uiState.words)
        binding.rvWords.smoothScrollToPosition(0)
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

    private fun handleViewEvent(viewEvent: MainViewEvent) {
        when (viewEvent) {
            is MainViewEvent.RefreshData -> {

            }
        }
    }

    private fun setEditListener() {
        binding.etSearchWord.doOnTextChanged { text, _, _, _ ->
            viewModel.searchWord(text.toString())
        }
    }

    private fun onItemClicked(item: WordUI) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailDialogFragment(item)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}