package com.kalex.catsapplication.catsList.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalex.catsapplication.catsList.data.usecase.CatsListUseCase
import com.kalex.catsapplication.catsList.models.CatItemDto
import com.kalex.catsapplication.utils.UseCaseFlowStatus
import com.kalex.catsapplication.utils.ViewModelNewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsListViewModel @Inject constructor(
    private val catsListUseCase: CatsListUseCase,
) : ViewModel() {
    private val _catsListState = MutableStateFlow<ViewModelNewsUiState<List<CatItemDto>>>(
        ViewModelNewsUiState.Loading(true),
    )
    val catsListState: StateFlow<ViewModelNewsUiState<List<CatItemDto>>>
        get() = _catsListState

    fun getCatsBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            catsListUseCase.getCatsBreeds().collectLatest {
                when (it) {
                    is UseCaseFlowStatus.Error -> _catsListState.value = ViewModelNewsUiState.Error("Unknown error")
                    is UseCaseFlowStatus.Loading -> _catsListState.value = ViewModelNewsUiState.Loading(true)
                    is UseCaseFlowStatus.Success -> _catsListState.value = ViewModelNewsUiState.Success(it.data)
                }
            }
        }
    }
}
