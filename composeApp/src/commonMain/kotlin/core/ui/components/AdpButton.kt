package core.ui.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AdpButton(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enable,
        onClick = { if (!isLoading) onClick() },
        content = {
            Text(
                text = text
            )
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            disabledContentColor = Color.DarkGray,
            disabledBackgroundColor = Color.LightGray
        )
    )
}

@Preview
@Composable
fun AdpButtonPreview() {
    AdpButton(
        modifier = Modifier,
        enable = true,
        text = "Test button",
        isLoading = false,
        onClick = { }
    )
}
