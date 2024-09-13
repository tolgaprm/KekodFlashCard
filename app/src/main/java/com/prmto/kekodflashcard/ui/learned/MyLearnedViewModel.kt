package com.prmto.kekodflashcard.ui.learned

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.domain.SearchUseCase
import com.prmto.kekodflashcard.domain.ToggleFavoriteWordItemUseCase
import com.prmto.kekodflashcard.domain.learned.GetLearnedWordsUseCase
import com.prmto.kekodflashcard.domain.model.WordUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLearnedViewModel @Inject constructor(
    private val getLearnedWordsUseCase: GetLearnedWordsUseCase,
    private val toggleFavoriteWordItemUseCase: ToggleFavoriteWordItemUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyLearnedUiState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun getLearnedWords() {
        _uiState.update { it.copy(isLoading = true) }
        getLearnedWordsUseCase()
            .onEach { learnedWords ->
                _uiState.update {
                    it.copy(
                        learnedWords = learnedWords,
                        wordsBeforeSearch = learnedWords
                    )
                }
            }
            .launchIn(viewModelScope)

        _uiState.update { it.copy(isLoading = false) }
    }

    fun onFavoriteClicked(item: WordUI) {
        viewModelScope.launch {
            toggleFavoriteWordItemUseCase(item)
        }
    }

    fun searchWord(query: String) {
        _uiState.update { it.copy(isLoading = true) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val searchedWords = searchUseCase.search(
                query = query,
                wordList = uiState.value.learnedWords
            )
            _uiState.update {
                it.copy(
                    searchedWords = searchedWords,
                    learnedWords = if (query.isEmpty()) uiState.value.wordsBeforeSearch else searchedWords,
                    isLoading = false
                )
            }
        }
    }
}