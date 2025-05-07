package features.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.AdpError
import domain.models.Community
import domain.usecase.GetCommunityInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunityDetailViewModel(
    private val getCommunityInfo: GetCommunityInfo
): ViewModel() {

    private val _uiState = MutableStateFlow(CommunityDetailUiState())
    val uiState: StateFlow<CommunityDetailUiState> = _uiState
        .onStart {
            getCommunityDetails()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            CommunityDetailUiState()
        )

    private fun getCommunityDetails() {
        // TODO add kotlinx-coroutines-swing to make viewModelScope
        // available in desktop
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            getCommunityInfo.invoke().onSuccess { community ->
                _uiState.update { it.copy(loading = false, community = community, error = null) }
            }.onFailure {
                _uiState.update { it.copy(loading = false, error = null) }
            }
        }
    }
}

data class CommunityDetailUiState(
    val loading: Boolean = true,
    val community: Community? = null,
    val error: AdpError? = null
)