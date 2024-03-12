package com.lucasprioste.searchrepositorygithub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasprioste.searchrepositorygithub.R
import com.lucasprioste.searchrepositorygithub.core.paginator.DefaultPagination
import com.lucasprioste.searchrepositorygithub.domain.model.home_screen.UiStateHome
import com.lucasprioste.searchrepositorygithub.domain.model.pagination.PaginationInfo
import com.lucasprioste.searchrepositorygithub.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHome())
    val uiState = _uiState.asStateFlow()

    private val defaultInput = "Test"

    private val paginationHelper = MutableStateFlow(PaginationInfo(page = 1))
    private val paginator = DefaultPagination(
        initialKey = paginationHelper.value.page,
        onLoadUpdated = { load ->
            paginationHelper.update { it.copy(isLoading = load) }
            _uiState.update { it.copy(isLoading = load) }
        },
        onRequest = { nextPage ->
            val query = uiState.value.input.ifBlank { defaultInput }
            gitHubRepository.searchRepositories(query = query, page = nextPage)
        },
        getNextKey = {
            paginationHelper.update{ it.copy(page = it.page + 1) }
            paginationHelper.value.page
        },
        onError = { message ->
            paginationHelper.update { it.copy(error = message) }
            _uiState.update { it.copy(errorStringResource = R.string.something_went_wrong, repositories = emptyList()) }
        },
        onSuccess = { items, newKey ->
            val newList = uiState.value.repositories.toMutableList()
            newList.addAll(items)
            _uiState.update { it.copy(repositories = newList) }
            paginationHelper.update { it.copy(page = newKey, endReached = items.isEmpty()) }
        }
    )

    private var lastSearchInput = defaultInput
    private var jobSearch: TimerTask? = null

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
                if (!paginationHelper.value.isLoading && uiState.value.repositories.isNotEmpty() && !paginationHelper.value.endReached)
                    loadNextItems()
            }
            is HomeEvent.OnSearchQueryChange -> {
                jobSearch?.cancel()

                _uiState.update {
                    it.copy(input = event.newValue)
                }

                jobSearch = Timer().schedule(500){
                    if (event.newValue.isNotBlank())
                        loadNewRepositories()
                }
            }
            HomeEvent.SearchCharacter -> {
                if (uiState.value.input.isBlank() || lastSearchInput == uiState.value.input) return
                loadNewRepositories()
            }
        }
    }

    private fun loadNewRepositories(){
        lastSearchInput = uiState.value.input

        paginationHelper.update { PaginationInfo(page = 1) }
        paginator.reset(key = 1)

        _uiState.update { it.copy(repositories = emptyList()) }
        loadNextItems()
    }
}