@file:OptIn(ExperimentalFoundationApi::class)

package features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import core.DomainInjector

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    vm: HomeViewModel = viewModel {
        HomeViewModel(DomainInjector.getEventsList)
    }
) {

    val uiState by vm.uiState.collectAsState()

    HomeScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier, uiState: HomeUiState) {
    LazyColumn(
        modifier = modifier
    ) {
        stickyHeader(key = "header") {
            Text("Android Dev Perú")
        }
        items(uiState.events.size) { index ->
            Text(uiState.events[index].title)
        }
    }
}