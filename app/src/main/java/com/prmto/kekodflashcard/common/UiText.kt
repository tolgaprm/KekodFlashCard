package com.prmto.kekodflashcard.common

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class UiText : Parcelable {

    @Parcelize
    data class StringRes(
        @androidx.annotation.StringRes val resId: Int,
        val formatArgs: List<String> = emptyList()
    ) : UiText(), Parcelable {
    }

    @Parcelize
    data class DynamicText(val value: String) : UiText(), Parcelable

    fun asString(context: Context): String {
        return when (this) {
            is StringRes -> context.getString(resId, *formatArgs.toTypedArray())
            is DynamicText -> value
        }
    }
}