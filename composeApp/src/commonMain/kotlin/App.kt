import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import core.ui.AdpDestination
import core.ui.AdpTheme
import core.ui.components.AdpBottomNavigationBar
import features.HomeRoute

@Composable
fun App() {
    AdpTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
            bottomBar = {
                AdpBottomNavigationBar(
                    modifier = Modifier,
                    onHomeTap = {
                        navController.navigate(AdpDestination.Home.route) {
                            this.launchSingleTop = true
                        }
                    },
                    onInfoTap = {
                        navController.navigate(AdpDestination.CommunityDetails.route) {
                            this.launchSingleTop = true
                        }
                    }
                )
            }
        ) {
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
}