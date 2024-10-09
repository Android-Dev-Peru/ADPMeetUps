package core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private object Theme {

    val DARK_GREEN = Color(56, 118, 29)
    val LIGHT_GREEN = Color(166, 199, 66)
    val LIGHT_BLUE = Color(174, 203, 250)
    val GRAY = Color(180, 180, 180)

    data class ThemeColor(
        val light: Color,
        val dark: Color = light
    )

    val primary = ThemeColor(
        light = Color.Black,
        dark = DARK_GREEN,
    )
    val secondary = ThemeColor(
        light = GRAY,
        dark = LIGHT_GREEN,
    )
    val background = ThemeColor(
        light = Color.White,
        dark = Color.Black,
    )
    val surface = ThemeColor(
        light = Color(248, 249, 250),
        dark = Color(32, 33, 36),
    )
    val onPrimary = ThemeColor(
        light = Color.White,
        dark = Color.White,
    )
    val onSecondary = ThemeColor(
        light = Color.Black,
        dark = Color.Black,
    )
    val onBackground = ThemeColor(
        light = Color(60, 64, 67),
        dark = Color(220, 220, 220),
    )
    val onSurface = ThemeColor(
        light = Color.Black,
        dark = Color.White,
    )
}

private val DarkColors = darkColors(
    primary = Theme.primary.dark,
    onPrimary = Theme.onPrimary.dark,
    secondary = Theme.secondary.dark,
    onSecondary = Theme.onSecondary.dark,
    background = Theme.background.dark,
    onBackground = Theme.onBackground.dark,
    surface = Theme.surface.dark,
    onSurface = Theme.onSurface.dark,
    error = Color.Red,
    onError = Color.White,
    primaryVariant = Theme.primary.dark,
    secondaryVariant = Theme.secondary.dark
)

private val LightColors = darkColors(
    primary = Theme.primary.light,
    onPrimary = Theme.onPrimary.light,
    secondary = Theme.secondary.light,
    onSecondary = Theme.onSecondary.light,
    background = Theme.background.light,
    onBackground = Theme.onBackground.light,
    surface = Theme.surface.light,
    onSurface = Theme.onSurface.light,
    error = Color.Red,
    onError = Color.White,
    primaryVariant = Theme.primary.light,
    secondaryVariant = Theme.secondary.light
)

@Composable
fun AdpTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content
    )
}

fun Color.darken(factor: Float): Color {
    return Color(
        red = red * (1 - factor),
        green = green * (1 - factor),
        blue = blue * (1 - factor),
        alpha = alpha
    )
}