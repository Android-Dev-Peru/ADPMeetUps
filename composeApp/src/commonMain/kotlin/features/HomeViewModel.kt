package features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.AdpError
import domain.Event
import domain.usecase.GetEventList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getEventList: GetEventList
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getEvents() {
        // TODO add kotlinx-coroutines-swing to make viewModelScope
        // available in desktop
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            getEventList.invoke(2024).onSuccess { events ->
                _uiState.update { it.copy(loading = false, events = events, error = null) }
            }.onFailure {
                _uiState.update { it.copy(loading = false, error = null) }
            }
        }
    }

}

data class HomeUiState(
    val loading: Boolean = true,
    val events: List<Event> = emptyList(),
    val error: AdpError? = null
)