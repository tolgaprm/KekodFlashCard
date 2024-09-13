package com.prmto.kekodflashcard.ui.detaildialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.domain.InsertLearnedWordUseCase
import com.prmto.kekodflashcard.domain.RemoveLearnedWordUseCase
import com.prmto.kekodflashcard.domain.model.WordUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailDialogViewModel @Inject constructor(
    private val insertLearnedWordUseCase: InsertLearnedWordUseCase,
    private val removeLearnedWordUseCase: RemoveLearnedWordUseCase
) : ViewModel() {

    fun onLearnedItemClicked(
        wordUI: WordUI,
        isFromLearnedScreen: Boolean
    ) {
        viewModelScope.launch {
            if (isFromLearnedScreen) {
                removeLearnedWordUseCase(wordUI)
            } else {
                insertLearnedWordUseCase(wordUI)
            }
        }
    }
}