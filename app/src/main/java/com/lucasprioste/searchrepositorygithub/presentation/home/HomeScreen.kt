package com.lucasprioste.searchrepositorygithub.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lucasprioste.searchrepositorygithub.R
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository
import com.lucasprioste.searchrepositorygithub.presentation.home.components.EmptyListRepositories
import com.lucasprioste.searchrepositorygithub.presentation.home.components.RepositoriesList
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.HorizontalPadding
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.InputShape
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.PaddingTopListRepositories

@Composable
fun HomeScreen(
    repositories: List<Repository>,
    searchInput: String,
    isLoading: Boolean,
    onEvent: (HomeEvent) -> Unit,
){
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = HorizontalPadding)
        ) {
            EmptyListRepositories(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = PaddingTopListRepositories),
                isEmpty = repositories.isEmpty() && !isLoading
            )

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
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = HorizontalPadding)
                .windowInsetsPadding(insets = WindowInsets.statusBars)
                .fillMaxWidth()
                .background(color = Color.Transparent, shape = InputShape),
            enabled = !isLoading,
            value = searchInput,
            onValueChange = {
                onEvent(
                    HomeEvent.OnSearchQueryChange(it)
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_search),
                    contentDescription = stringResource(id = R.string.search_icon_desc),
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent(HomeEvent.SearchCharacter)
                    focusManager.clearFocus()
                },
            ),
            shape = InputShape,
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenInitPreview(){
    HomeScreen(
        repositories = emptyList(),
        isLoading = false,
        searchInput = "",
        onEvent = {}
    )
}