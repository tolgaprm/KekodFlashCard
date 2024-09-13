package com.prmto.kekodflashcard.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.UiText
import com.prmto.kekodflashcard.common.doOnError
import com.prmto.kekodflashcard.common.doOnLoading
import com.prmto.kekodflashcard.common.doOnSuccess
import com.prmto.kekodflashcard.domain.GetWordsUseCase
import com.prmto.kekodflashcard.domain.SearchUseCase
import com.prmto.kekodflashcard.domain.ToggleFavoriteWordItemUseCase
import com.prmto.kekodflashcard.domain.model.WordUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase,
    private val toggleFavoriteWordItemUseCase: ToggleFavoriteWordItemUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _categoryItems = MutableStateFlow<List<CategoryItem>>(listOf())
    val categoryItems = _categoryItems.asStateFlow()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<MainViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    private var firstTime = true

    private var favoriteItemsCount = 0

    private var searchJob: Job? = null


    init {
        setCategoryItems()
    }

    private fun setCategoryItems() {
        _categoryItems.value = listOf(
            CategoryItem(
                iconRes = R.drawable.ic_star_yellow,
                title = UiText.StringRes(R.string.my_favorites),
                subtitle = UiText.StringRes(
                    R.string.my_favorites_subtitle,
                    listOf(favoriteItemsCount.toString())
                )
            ),
            CategoryItem(
                iconRes = R.drawable.ic_ai_yellow,
                title = UiText.StringRes(R.string.exercise_with_ai),
                subtitle = UiText.StringRes(R.string.exercise_with_ai_subtitle)
            )
        )
    }

    fun getWords(
        isShuffle: Boolean = false
    ) {
        getWordsUseCase()
            .doOnLoading {
                _uiState.update {
                    it.copy(
                        loading = true,
                        errorMessage = null
                    )
                }
            }
            .doOnSuccess { words ->
                _uiState.update {
                    it.copy(
                        words = if (isShuffle) words.shuffled() else words,
                        wordsBeforeSearch = words,
                        loading = false,
                        errorMessage = null
                    )
                }
                updateFavoriteCount(words.count { it.isFavorite })
                viewModelScope.launch {
                    if (isShuffle && !firstTime) {
                        _viewEvent.emit(MainViewEvent.RefreshData)
                    }
                    firstTime = false
                }
            }
            .doOnError { errorMessage ->
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = errorMessage
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteClicked(item: WordUI) {
        viewModelScope.launch { toggleFavoriteWordItemUseCase(item) }
    }

    private fun updateFavoriteCount(newCount: Int) {
        favoriteItemsCount = newCount
        _categoryItems.update { categoryItems ->
            categoryItems.map { categoryItem ->
                if (categoryItem.title == UiText.StringRes(R.string.my_favorites)) {
                    categoryItem.copy(
                        subtitle = UiText.StringRes(
                            R.string.my_favorites_subtitle,
                            listOf(favoriteItemsCount.toString())
                        )
                    )
                } else {
                    categoryItem
                }
            }
        }
    }

    fun searchWord(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                loading = true,
            )
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val searchedWords = searchUseCase.search(query, uiState.value.words)
            _uiState.update {
                it.copy(
                    searchedWords = searchedWords,
                    words = if (query.isEmpty()) uiState.value.wordsBeforeSearch else searchedWords,
                    loading = false
                )
            }
        }
    }

    fun refreshWords() {
        getWords(isShuffle = true)
    }
}