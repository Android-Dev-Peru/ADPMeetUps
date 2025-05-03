package features.eventDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.AdpError
import domain.models.Event
import domain.usecase.GetEventById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getEventById: GetEventById
): ViewModel() {

    private val eventId: String = checkNotNull(savedStateHandle["eventId"])

    private val _uiState = MutableStateFlow(EventDetailUiState())
    val uiState: StateFlow<EventDetailUiState> = _uiState
        .onStart {
            getEventDetails(eventId)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            EventDetailUiState()
        )

    private fun getEventDetails(id: String) {
        // TODO add kotlinx-coroutines-swing to make viewModelScope
        // available in desktop
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            getEventById.invoke(id).onSuccess { event ->
                _uiState.update { it.copy(loading = false, event = event, error = null) }
            }.onFailure {
                _uiState.update { it.copy(loading = false, error = null) }
            }
        }
    }
}

data class EventDetailUiState(
    val loading: Boolean = true,
    val event: Event? = null,
    val error: AdpError? = null
)