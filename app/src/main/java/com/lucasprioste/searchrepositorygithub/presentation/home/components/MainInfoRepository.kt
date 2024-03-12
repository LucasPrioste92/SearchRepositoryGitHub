package com.lucasprioste.searchrepositorygithub.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lucasprioste.searchrepositorygithub.R
import com.lucasprioste.searchrepositorygithub.core.util.toReadableDate
import com.lucasprioste.searchrepositorygithub.presentation.core.util.getLocale
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.SpaceItemsMainInfoRepository
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.Yellow
import java.time.LocalDateTime

@Composable
fun MainInfoRepository(
    modifier: Modifier = Modifier,
    name: String,
    description: String? = null,
    language: String? = null,
    score: String,
    dateCreation: LocalDateTime,
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            language?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(SpaceItemsMainInfoRepository))
            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = R.drawable.icon_star),
                contentDescription = stringResource(id = R.string.icon_star_desc),
                tint = Yellow
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = score,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(SpaceItemsMainInfoRepository))
        }
        Row {
            Text(
                text = "${stringResource(id = R.string.created_at)} ",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = dateCreation.toReadableDate(getLocale()),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}