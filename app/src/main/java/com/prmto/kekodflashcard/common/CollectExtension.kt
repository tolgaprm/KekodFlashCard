package com.prmto.kekodflashcard.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collect(
    lifecycleOwner: LifecycleOwner,
    function: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect {
                function(it)
            }
        }
    }
}

fun <T> Fragment.collectFlow(
    flow: Flow<T>,
    action: suspend (T) -> Unit
): Job {
    return viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                action(it)
            }
        }
    }
}

fun <T> Fragment.collectEvent(
    flow: Flow<T>,
    action: suspend (T) -> Unit
): Job {
    return viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main.immediate) {
        flow.collect {
            action(it)
        }
    }
}

