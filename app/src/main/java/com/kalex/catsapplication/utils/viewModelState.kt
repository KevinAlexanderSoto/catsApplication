package com.kalex.catsapplication.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.handleViewModelState(
    call: Flow<ViewModelNewsUiState<T>>,
    onSuccess: (T) -> Unit,
    onLoading: (Boolean) -> Unit,
    onError: (String) -> Unit,
) {
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            call.collectLatest {
                when (it) {
                    is ViewModelNewsUiState.Error -> onError.invoke(it.exception)
                    is ViewModelNewsUiState.Loading -> onLoading.invoke(it.isLoading)
                    is ViewModelNewsUiState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }
}