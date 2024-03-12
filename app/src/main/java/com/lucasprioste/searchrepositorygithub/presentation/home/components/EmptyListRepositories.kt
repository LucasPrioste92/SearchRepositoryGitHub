package com.lucasprioste.searchrepositorygithub.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasprioste.searchrepositorygithub.R
import com.lucasprioste.searchrepositorygithub.presentation.core.util.shimmerEffect
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ArrangementCardRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ElevationCard
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.HeightCardRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ShapeCardRepository

@Composable
fun EmptyListRepositories(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    Column(
        modifier = modifier,
    ) {
        when(isLoading){
            true -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ArrangementCardRepository)
                ){
                    (0..3).forEach { _ ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(HeightCardRepository)
                                .shadow(elevation = ElevationCard, shape = ShapeCardRepository)
                                .shimmerEffect()
                        )
                    }
                }
            }
            false -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp),
                        painter = painterResource(id = R.drawable.icon_not_found),
                        contentDescription = stringResource(id = R.string.not_found_repo_desc_img),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.not_found_repo),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyListPreview(){
    EmptyListRepositories(
        modifier = Modifier
            .fillMaxSize(),
        isLoading = false
    )
}