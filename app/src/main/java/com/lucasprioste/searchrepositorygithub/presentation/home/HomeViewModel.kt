package com.lucasprioste.searchrepositorygithub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasprioste.searchrepositorygithub.core.paginator.DefaultPagination
import com.lucasprioste.searchrepositorygithub.domain.model.home_screen.UiStateHome
import com.lucasprioste.searchrepositorygithub.domain.model.pagination.PaginationInfo
import com.lucasprioste.searchrepositorygithub.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHome())
    val uiState = _uiState.asStateFlow()

    private val paginationHelper = MutableStateFlow(PaginationInfo(page = 1))
    private val paginator = DefaultPagination(
        initialKey = paginationHelper.value.page,
        onLoadUpdated = { load ->
            paginationHelper.update { it.copy(isLoading = load) }
        },
        onRequest = { nextPage ->
            gitHubRepository.searchRepositories(query = "teste", page = nextPage)
        },
        getNextKey = {
            paginationHelper.update{ it.copy(page = it.page + 1) }
            paginationHelper.value.page
        },
        onError = { message ->
            paginationHelper.update { it.copy(error = message) }
        },
        onSuccess = { items, newKey ->
            val newList = uiState.value.repositories.toMutableList()
            newList.addAll(items)
            _uiState.update { it.copy(repositories = newList) }
            paginationHelper.update { it.copy(page = newKey, endReached = items.isEmpty()) }
        }
    )

    init {
        loadNextItems()
    }

    private fun loadNextItems(){
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

    fun onEvent(event: HomeEvent){
        when(event){
            HomeEvent.LoadMore -> {
                if (!paginationHelper.value.isLoading && uiState.value.repositories.isNotEmpty() && !paginationHelper.value.endReached) {
                    loadNextItems()
                }
            }
        }
    }
}