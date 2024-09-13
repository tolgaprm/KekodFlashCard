package com.prmto.kekodflashcard.ui.detaildialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.kekodflashcard.domain.InsertLearnedWordUseCase
import com.prmto.kekodflashcard.domain.model.WordUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailDialogViewModel @Inject constructor(
    private val insertLearnedWordUseCase: InsertLearnedWordUseCase
) : ViewModel() {

    fun onLearnedItemClicked(wordUI: WordUI) {
        viewModelScope.launch {
            insertLearnedWordUseCase(wordUI)
        }
    }
}