package com.prmto.kekodflashcard.common

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class UiText : Parcelable {

    @Parcelize
    data class StringRes(
        @androidx.annotation.StringRes val resId: Int
    ) : UiText(), Parcelable

    @Parcelize
    data class DynamicText(val value: String) : UiText(), Parcelable

    fun asString(context: Context): String {
        return when (this) {
            is StringRes -> context.getString(resId)
            is DynamicText -> value
        }
    }
}