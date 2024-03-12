package com.lucasprioste.searchrepositorygithub.presentation.home

sealed class HomeEvent{
    data object LoadMore: HomeEvent()
}
