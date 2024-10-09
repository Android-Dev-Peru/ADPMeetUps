import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
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
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
            bottomBar = {
                AdpBottomNavigationBar(
                    modifier = Modifier,
                    onHomeTap = {
                        navController.navigate(AdpDestination.Home.route) {
                            this.launchSingleTop = true
                        }
                    },
                    onLiveEventTap = {
                        navController.navigate(AdpDestination.LiveEvent.route) {
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
                    HomeRoute(
                        onEventTap = {
                            navController.navigate(AdpDestination.EventDetail.route)
                        }
                    )
                }
                composable(
                    route = AdpDestination.EventDetail.route,
                    arguments = listOf(navArgument("eventId", builder = { type = StringType }))
                ) {
                    // EventDetailScreen(eventId = it.arguments?.getString("eventId"))
                }
                composable(
                    route = AdpDestination.CommunityDetails.route,
                    arguments = listOf(navArgument("eventId", builder = { type = StringType }))
                ) {
                    // CommunityDetailScreen()
                }
                composable(
                    route = AdpDestination.LiveEvent.route,
                    arguments = listOf(navArgument("eventId", builder = { type = StringType }))
                ) {
                    // LiveEventScreen()
                }
            }
        }

    }
}