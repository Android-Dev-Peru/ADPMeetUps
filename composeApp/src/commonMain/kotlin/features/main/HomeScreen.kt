@file:OptIn(ExperimentalFoundationApi::class)

package features.main

import adpmeetups.composeapp.generated.resources.Res
import adpmeetups.composeapp.generated.resources.home_events_list
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import core.DomainInjector
import domain.models.Event
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeRoute(
    onEventTap: (Event) -> Unit,
    vm: HomeViewModel = viewModel {
        HomeViewModel(DomainInjector.getEventsList)
    }
) {

    val uiState by vm.uiState.collectAsState()

    HomeScreen(
        modifier = Modifier.background(MaterialTheme.colors.background),
        uiState = uiState,
        onEventTap = onEventTap
    )
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier, uiState: HomeUiState, onEventTap: (Event) -> Unit) {
    LazyColumn(
        modifier = modifier,
    ) {
        stickyHeader(key = "header") {
            HomeScreenHeader(modifier = Modifier.fillMaxWidth())
        }
        items(uiState.events.size) { index ->
            EventItem(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                event = uiState.events[index],
                onTap = onEventTap
            )
        }
    }
}

@Composable
private fun HomeScreenHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.background(MaterialTheme.colors.surface).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.home_events_list),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun EventItem(modifier: Modifier = Modifier, event: Event, onTap: (Event) -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onTap(event)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .widthIn(max = 130.dp),
                model = ImageRequest
                    .Builder(LocalPlatformContext.current)
                    .data(event.eventBannerUrl)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .build(),
                contentDescription = event.title,
                contentScale = ContentScale.Crop,
            )
            Column {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary
                    )
                    Text(
                        modifier = Modifier.weight(1.0f),
                        text = event.date.toString(),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.secondary
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Place,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary
                    )
                    Text(
                        modifier = Modifier.weight(1.0f),
                        text = event.addressInfo,
                        style = MaterialTheme.typography.caption,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
        Text(
            text = event.description,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
