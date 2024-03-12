package com.lucasprioste.searchrepositorygithub.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lucasprioste.searchrepositorygithub.presentation.home.HomeScreen
import com.lucasprioste.searchrepositorygithub.presentation.home.HomeViewModel

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.route,
    ) {
        composable(
            route = Route.HomeScreen.route,
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            HomeScreen(
                repositories = uiState.repositories,
                isLoading = uiState.isLoading,
                onEvent = viewModel::onEvent
            )
        }
        composable(
            route = Route.DetailRepositoryScreen.route,
        ) {

        }
    }
}