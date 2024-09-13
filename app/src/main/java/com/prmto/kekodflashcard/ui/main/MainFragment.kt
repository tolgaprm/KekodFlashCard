package com.prmto.kekodflashcard.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.prmto.kekodflashcard.common.collectEvent
import com.prmto.kekodflashcard.common.collectFlow
import com.prmto.kekodflashcard.databinding.FragmentMainBinding
import com.prmto.kekodflashcard.domain.model.WordUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private val wordAdapter by lazy {
        WordAdapter(
            onItemClick = ::onItemClicked,
            onFavoriteClick = viewModel::onFavoriteClicked
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
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWords()
        }
        collectFlow(viewModel.categoryItems, ::setCategoryItems)
        collectFlow(viewModel.uiState, ::setUiState)
        collectEvent(viewModel.viewEvent, ::handleViewEvent)
    }

    private fun setUiState(uiState: MainUiState) {
        binding.swipeRefreshLayout.isRefreshing = uiState.loading
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

    private fun handleViewEvent(viewEvent: MainViewEvent) {
        when (viewEvent) {
            is MainViewEvent.RefreshData -> {
                Snackbar.make(
                    binding.root,
                    "Scroll to the top to see the new words",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Scroll Top") {
                    binding.rvWords.smoothScrollToPosition(0)
                }.show()
            }
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