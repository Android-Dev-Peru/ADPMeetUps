package features

import androidx.compose.runtime.Composable
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

    HomeScreen(
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {

}