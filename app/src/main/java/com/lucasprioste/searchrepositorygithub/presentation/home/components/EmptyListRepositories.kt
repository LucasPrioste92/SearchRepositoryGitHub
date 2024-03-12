package com.lucasprioste.searchrepositorygithub.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasprioste.searchrepositorygithub.R

@Composable
fun EmptyListRepositories(
    modifier: Modifier = Modifier,
    isEmpty: Boolean
) {
    AnimatedVisibility(visible = isEmpty) {
        Column(
            modifier = modifier
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

@Preview(showBackground = true)
@Composable
fun EmptyListPreview(){
    EmptyListRepositories(
        modifier = Modifier
            .fillMaxSize(),
        isEmpty = true
    )
}