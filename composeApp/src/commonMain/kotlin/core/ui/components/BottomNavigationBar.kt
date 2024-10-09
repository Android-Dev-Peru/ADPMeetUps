package core.ui.components

import adpmeetups.composeapp.generated.resources.Res
import adpmeetups.composeapp.generated.resources.screenTitle_community_info
import adpmeetups.composeapp.generated.resources.screenTitle_home
import adpmeetups.composeapp.generated.resources.screenTitle_live_event
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource

@Composable
fun AdpBottomNavigationBar(
    modifier: Modifier = Modifier,
    onHomeTap: () -> Unit,
    onLiveEventTap: () -> Unit,
    onInfoTap: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        cutoutShape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)
        ) {
            IconButton(
                onClick = onHomeTap,
                content = {
                    Icon(Icons.Filled.Home, contentDescription = stringResource(Res.string.screenTitle_home))
                }
            )

            IconButton(
                onClick = onLiveEventTap,
                content = {
                    Icon(Icons.Filled.PlayArrow, contentDescription = stringResource(Res.string.screenTitle_live_event))
                }
            )

            IconButton(
                onClick = onInfoTap,
                content = {
                    Icon(Icons.Filled.Info, contentDescription = stringResource(Res.string.screenTitle_community_info))
                }
            )
        }
    }
}