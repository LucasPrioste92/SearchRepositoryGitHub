package com.lucasprioste.searchrepositorygithub.presentation.core.navigation

sealed class Route(val route: String){
    data object HomeScreen: Route("home_screen")
    data object DetailRepositoryScreen: Route("detail_repository_screen")
}