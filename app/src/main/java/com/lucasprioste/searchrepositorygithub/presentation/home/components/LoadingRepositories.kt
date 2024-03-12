package com.lucasprioste.searchrepositorygithub.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.lucasprioste.searchrepositorygithub.presentation.core.util.shimmerEffect
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ArrangementCardRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ElevationCard
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.HeightCardRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.ShapeCardRepository

@Composable
fun LoadingRepositories(
    modifier: Modifier = Modifier,
    isLoading: Boolean
){
    AnimatedVisibility(visible = isLoading) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(ArrangementCardRepository)
        ) {
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
}