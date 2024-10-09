package core.ui.components

import adpmeetups.composeapp.generated.resources.Res
import adpmeetups.composeapp.generated.resources.screenTitle_community_info
import adpmeetups.composeapp.generated.resources.screenTitle_home
import androidx.compose.foundation.layout.Row
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource

@Composable
fun AdpBottomNavigationBar(
    modifier: Modifier = Modifier,
    onHomeTap: () -> Unit,
    onInfoTap: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Row {
            IconButton(
                onClick = onHomeTap,
                content = {
                    Icon(Icons.Filled.Home, contentDescription = stringResource(Res.string.screenTitle_home))
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