package com.kalex.catsapplication.catsList.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kalex.catsapplication.catsList.models.CatItemDto
import com.kalex.catsapplication.catsList.presentation.composables.CatListColum
import com.kalex.catsapplication.catsList.presentation.composables.LoadingBar
import com.kalex.catsapplication.catsList.presentation.viewmodel.CatsListViewModel
import com.kalex.catsapplication.utils.handleViewModelState
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CatsListFragment : Fragment() {

    private val catsViewModel: CatsListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    catsViewModel.getCatsBreeds()
                    handleCatListState()
                }
            }
        }
    }

    @Composable
    private fun handleCatListState() {
        val data = remember { mutableStateListOf<CatItemDto>() }
        var isLoading by remember { mutableStateOf(true) }

        handleViewModelState(
            catsViewModel.catsListState,
            onSuccess = {
                data.clear()
                data += it
                isLoading = false
            },
            onLoading = {
                isLoading = it
            },
            onError = {
                isLoading = false
            },
        )
        LoadingBar(isLoading)
        if (data.isNotEmpty()) {
            CatListColum(data)
        }
    }
}
