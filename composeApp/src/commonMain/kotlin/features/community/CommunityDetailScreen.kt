package features.community

import adpmeetups.composeapp.generated.resources.Res
import adpmeetups.composeapp.generated.resources.communityTitle_ex_organizer
import adpmeetups.composeapp.generated.resources.communityTitle_organizer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import core.DomainInjector
import domain.models.Community
import domain.models.Organizer
import domain.models.SocialMedia
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommunityDetailScreen(
    vm: CommunityDetailViewModel = viewModel {
        CommunityDetailViewModel(
            DomainInjector.getCommunityInfo
        )
    }
) {

    val uiState by vm.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    uiState.community?.let {
        CommunityDetailLayout(
            modifier = Modifier.background(MaterialTheme.colors.background),
            community = it,
            organizers = it.organizers,
            exOrganizers = it.exOrganizers,
            socialMedia = it.socialMedia,
            uriHandler = uriHandler
        )
    }
}

@Composable
private fun CommunityDetailLayout(
    modifier: Modifier = Modifier,
    community: Community?,
    organizers: List<Organizer>,
    exOrganizers: List<Organizer>,
    socialMedia: List<SocialMedia>,
    uriHandler: UriHandler
) {
    LazyColumn (
        modifier = modifier.fillMaxSize()
    ) {
        item {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                model= community?.topBanner,
                contentDescription = community?.name,
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = community?.name.orEmpty(),
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(text = community?.description.orEmpty())
            }

            Spacer(modifier = Modifier.size(8.dp))
        }

        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(Res.string.communityTitle_organizer)
            )
        }

        organizers.forEach { organizer ->
            item {
                OrganizerItem(organizer = organizer)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(Res.string.communityTitle_ex_organizer)
            )
        }

        exOrganizers.forEach { exOrganizer ->
            item {
                OrganizerItem(organizer = exOrganizer)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        item {
            SocialMediaBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                socialMedia = socialMedia,
                uriHandler = uriHandler
            )
        }
    }
}

@Composable
fun OrganizerItem(modifier: Modifier = Modifier, organizer: Organizer?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(androidx.compose.foundation.shape.CircleShape)
                .size(80.dp),
            contentScale = ContentScale.Crop,
            model = organizer?.photo,
            contentDescription = organizer?.name,
        )

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = organizer?.name.orEmpty(),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun SocialMediaBar(
    modifier: Modifier = Modifier,
    socialMedia: List<SocialMedia>,
    uriHandler: UriHandler
) {
    LazyRow(
        modifier = modifier.fillMaxWidth().padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(socialMedia.size) { social ->
            SocialMediaItem(
                socialMedia = socialMedia[social],
                uriHandler = uriHandler
            )
        }
    }
}

@Composable
fun SocialMediaItem(
    modifier: Modifier = Modifier,
    socialMedia: SocialMedia,
    uriHandler: UriHandler
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(24.dp)
                .clickable {
                    uriHandler.openUri(socialMedia.url)
                },
            contentScale = ContentScale.Fit,
            model = ImageRequest
                .Builder(LocalPlatformContext.current)
                .data(socialMedia.icon)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = socialMedia.icon,
            colorFilter = ColorFilter.tint(
                if(isSystemInDarkTheme()) Color.White else Color.Black
            )
        )
    }
}
