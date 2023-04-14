package com.kalex.catsapplication.utils

sealed class ViewModelNewsUiState<T> {
    data class Success<T>(val data: T) : ViewModelNewsUiState<T>()
    data class Loading<T>(val isLoading: Boolean) : ViewModelNewsUiState<T>()
    data class Error<T>(val exception: String) : ViewModelNewsUiState<T>()
}

sealed class UseCaseFlowStatus<T> {
    data class Success<T>(val data: T) : UseCaseFlowStatus<T>()
    data class Error<T>(val exception: String) : UseCaseFlowStatus<T>()
    data class Loading<T>(val message: String) : UseCaseFlowStatus<T>()
}