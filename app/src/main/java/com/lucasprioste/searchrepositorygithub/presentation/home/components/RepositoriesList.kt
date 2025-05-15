package com.lucasprioste.searchrepositorygithub.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Owner
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository
import com.lucasprioste.searchrepositorygithub.presentation.core.util.OnBottomReached
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ArrangementCardRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ElevationCard
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.PaddingTopListRepositories
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.RepositoryCardHorizontalPadding
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.RepositoryCardVerticalPadding
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ShapeCardRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.TopPadding
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.TopPaddingContent
import java.time.LocalDateTime

@Composable
fun RepositoriesList(
    modifier: Modifier = Modifier,
    items: List<Repository>,
    loadMore: () -> Unit,
    isLoading: Boolean,
){
    val scrollState = rememberLazyListState()

    scrollState.OnBottomReached {
        loadMore()
    }

    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(ArrangementCardRepository),
        contentPadding = PaddingValues(top = TopPadding + TopPaddingContent + WindowInsets.safeContent.asPaddingValues().calculateTopPadding())
    ){
        items(items){repository ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = ShapeCardRepository,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = ElevationCard
                ),
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            vertical = RepositoryCardVerticalPadding,
                            horizontal = RepositoryCardHorizontalPadding
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MainInfoRepository(
                        modifier = Modifier
                            .weight(1f),
                        name = repository.name,
                        description = repository.description,
                        score = repository.score.toString(),
                        language = repository.language,
                        dateCreation = repository.createdAt
                    )
                    repository.owner?.let { owner ->
                        Spacer(modifier = Modifier.width(8.dp))
                        OwnerRepositoryInfo(
                            name = owner.name,
                            imageUrl = owner.imageUrl
                        )
                    }
                }
            }
        }
        item {
            AnimatedVisibility(visible = isLoading) {
                LoadingRepositories(
                    modifier = Modifier
                        .fillMaxSize(),
                    isLoading = isLoading
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoriesListPreview(){
    RepositoriesList(
        modifier = Modifier
            .fillMaxSize(),
        items = listOf(
            Repository(
                id = 1,
                name = "Test",
                fullName = "asads/Test",
                private = false,
                owner = Owner(name = "Lucas", imageUrl = "https://avatars.githubusercontent.com/u/69161901?s=96&v=4"),
                description = "Repository Test",
                fork = false,
                language = "Kotlin",
                score = 2,
                watchersCount = 2,
                openIssuesCount = 2,
                forksCount = 1,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        ),
        loadMore = {},
        isLoading = true
    )
}