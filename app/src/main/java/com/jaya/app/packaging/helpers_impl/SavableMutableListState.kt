package com.jaya.app.packaging.helpers_impl

import androidx.lifecycle.SavedStateHandle
import com.jaya.app.packaging.utility.UiData

class SavableMutableListState<T : Iterable<*>>(
    private val key: UiData,
    private val savedStateHandle: SavedStateHandle,
    initialData: T,
) {
    val value = savedStateHandle.getStateFlow(key = key.name, initialValue = initialData)

    fun emit(data: T) {
        savedStateHandle[key.name] = data
    }
}