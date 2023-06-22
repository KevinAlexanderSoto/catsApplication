package com.kalex.catsapplication.catsList.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kalex.catsapplication.R
import com.kalex.catsapplication.catsList.models.CatItemDto
import com.kalex.catsapplication.catsList.presentation.composables.CatListColum
import com.kalex.catsapplication.catsList.presentation.composables.LoadingBar
import com.kalex.catsapplication.catsList.presentation.composables.PagingCatListColum
import com.kalex.catsapplication.catsList.presentation.composables.PagingCatListColum2
import com.kalex.catsapplication.catsList.presentation.viewmodel.CatsListViewModel
import com.kalex.catsapplication.utils.handleViewModelState
import dagger.hilt.android.AndroidEntryPoint

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
                    /**Old cat list implementation**/
                    // catsViewModel.getCatsBreeds()
                    // HandleCatListState()
                    PagingCatListColum2(getString(R.string.catList_title))

                   /*
                    //Paging with paging3
                    val cats: LazyPagingItems<CatItemDto> =
                        catsViewModel.getPagingCatsBreeds().collectAsLazyPagingItems()
                    PagingCatListColum(cats,getString(R.string.catList_title))*/
                }
            }
        }
    }

    @Composable
    private fun HandleCatListState() {
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
