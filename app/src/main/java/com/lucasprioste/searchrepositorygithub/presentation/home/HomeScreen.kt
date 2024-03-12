package com.lucasprioste.searchrepositorygithub.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository
import com.lucasprioste.searchrepositorygithub.presentation.home.components.EmptyListRepositories
import com.lucasprioste.searchrepositorygithub.presentation.home.components.RepositoriesList
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.HorizontalPadding
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.TopPadding

@Composable
fun HomeScreen(
    repositories: List<Repository>,
    isLoading: Boolean,
    onEvent: (HomeEvent) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = HorizontalPadding)
            .padding(top = TopPadding)
    ) {
        AnimatedVisibility(visible = repositories.isEmpty()) {
            EmptyListRepositories(
                modifier = Modifier
                    .fillMaxSize(),
                isLoading = isLoading
            )
        }
        if (repositories.isNotEmpty()){
            RepositoriesList(
                modifier = Modifier
                    .fillMaxSize(),
                items = repositories,
                loadMore = {
                    onEvent(HomeEvent.LoadMore)
                },
                isLoading = isLoading
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenInitPreview(){
    HomeScreen(
        repositories = emptyList(),
        isLoading = false,
        onEvent = {}
    )
}