import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import core.ui.AdpDestination
import features.HomeRoute

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = AdpDestination.Home.route) {
            composable(AdpDestination.Home.route) {
                HomeRoute()
            }
            composable(
                route = AdpDestination.EventDetail.route,
                arguments = listOf(navArgument("eventId", builder = { type = StringType }))
            ) {
                // EventDetailScreen(eventId = it.arguments?.getString("eventId"))
            }
        }
    }
}