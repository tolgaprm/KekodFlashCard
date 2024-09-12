package com.prmto.kekodflashcard.ui.main

sealed class MainViewEvent {

    data object RefreshData : MainViewEvent()
}