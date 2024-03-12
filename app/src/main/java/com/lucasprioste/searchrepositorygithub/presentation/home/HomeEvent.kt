package com.lucasprioste.searchrepositorygithub.presentation.home

sealed class HomeEvent{
    data object LoadMore: HomeEvent()
    data object SearchCharacter: HomeEvent()
    data class OnSearchQueryChange(val newValue: String): HomeEvent()
}
