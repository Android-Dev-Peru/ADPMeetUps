package features.eventDetail

import PlatformName
import adpmeetups.composeapp.generated.resources.Res
import adpmeetups.composeapp.generated.resources.detailEvent_add_to_calendar_button
import adpmeetups.composeapp.generated.resources.detailEvent_toast_failure
import adpmeetups.composeapp.generated.resources.detailEvent_toast_success
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import core.DomainInjector
import core.ui.components.platform.showToast
import domain.Event
import domain.EventTalk
import domain.Speaker
import features.eventDetail.platform.CalendarManager
import getPlatformName
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.resources.stringResource

@Composable
fun EventDetailScreen(
    vm: EventDetailViewModel = viewModel {
        EventDetailViewModel(
            createSavedStateHandle(),
            DomainInjector.getEventDetail
        )
    }
) {

    val uiState by vm.uiState.collectAsState()
    val calendarManager = CalendarManager(LocalPlatformContext.current)

    EventDetailLayout(
        modifier = Modifier.background(MaterialTheme.colors.background),
        event = uiState.event,
        talks = uiState.event?.talks,
        calendarManager = calendarManager,
    )
}

@Composable
private fun EventDetailLayout(
    modifier: Modifier = Modifier,
    event: Event?,
    talks: List<EventTalk>?,
    calendarManager: CalendarManager,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize()
    ) {
        item {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                model= event?.eventBannerUrl,
                contentDescription = event?.title,
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = event?.title.orEmpty(),
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(text = event?.description.orEmpty())
            }

            Spacer(modifier = Modifier.size(8.dp))
        }

        talks?.forEach { talk ->
            item {
                EventTalkItem(talk = talk)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))

            when(getPlatformName()) {
                PlatformName.ANDROID, PlatformName.IOS -> {
                    event?.let {
                        CalendarButton(
                            title = it.title,
                            description = it.description,
                            startTime = it.startTime,
                            endTime = it.endTime,
                            location = it.addressInfo,
                            eventDate = it.date,
                            calendarManager = calendarManager
                        )
                    }
                }
                PlatformName.DESKTOP -> {
                    //Do nothing
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun EventTalkItem(modifier: Modifier = Modifier, talk: EventTalk?) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = talk?.title.orEmpty(),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = talk?.description.orEmpty(),
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.size(8.dp))

        talk?.bannerUrl.also {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                model = it,
                contentDescription = talk?.title,
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        talk?.speakers?.forEach { speaker ->
            SpeakerItem(speaker = speaker)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun SpeakerItem(modifier: Modifier = Modifier, speaker: Speaker?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(androidx.compose.foundation.shape.CircleShape)
                .size(80.dp),
            contentScale = ContentScale.Crop,
            model = speaker?.profilePicUrl,
            contentDescription = speaker?.name,
        )

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = speaker?.name.orEmpty(),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun CalendarButton(
    title: String,
    description: String,
    startTime: LocalTime,
    endTime: LocalTime,
    location: String = "",
    eventDate: LocalDate,
    calendarManager: CalendarManager
) {
    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current
    val successfulMessage = stringResource(Res.string.detailEvent_toast_success)
    val failureMessage = stringResource(Res.string.detailEvent_toast_failure)

    Button(
        onClick = {
            scope.launch {
                val success = calendarManager.addEventToCalendar(
                    title = title,
                    description = description,
                    startTime = startTime,
                    endTime = endTime,
                    location = location,
                    eventDate = eventDate
                )

                if (success) {
                    showToast(
                        message = successfulMessage,
                        isLongDuration = false,
                        context = context)
                } else {
                    showToast(
                        message = failureMessage,
                        isLongDuration = false,
                        context = context)
                }
            }
        }
    ) {
        Text(
            text = stringResource(Res.string.detailEvent_add_to_calendar_button)
        )
    }
}