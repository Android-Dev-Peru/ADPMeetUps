import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import features.eventDetail.EventDetailScreen
import features.main.HomeRoute

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
        ) { paddingValues ->
            NavHost(
                navController, startDestination = AdpDestination.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(AdpDestination.Home.route) {
                    HomeRoute(
                        onEventTap = { event ->
                            navController.navigate(
                                AdpDestination.EventDetail.route
                                    .replace("{eventId}", event.id)
                            )
                        }
                    )
                }
                composable(
                    route = AdpDestination.EventDetail.route,
                    arguments = listOf(
                        navArgument("eventId", builder = { type = StringType })
                    )
                ) {
                    EventDetailScreen()
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