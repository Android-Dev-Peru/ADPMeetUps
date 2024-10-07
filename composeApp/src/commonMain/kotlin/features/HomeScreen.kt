package features

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import core.DomainInjector
import core.collectAsStateMultiplatform

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    vm: HomeViewModel = viewModel {
        HomeViewModel(DomainInjector.getEventsList)
    }
) {

    val uiState by vm.uiState.collectAsStateMultiplatform()

    LaunchedEffect(Unit) {
        vm.getEvents()
    }

    HomeScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier, uiState: HomeUiState) {
    Scaffold(
        modifier = modifier
    ) {
        Text("Android Dev Perú App")
    }
}