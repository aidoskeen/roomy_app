package com.aidos.roomy_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val brightColors = lightColors(
    primary = RedA100,
    primaryVariant = DarkGray,
    secondary = DarkYellow,
    secondaryVariant = LightGray,
    onPrimary = Orange900,
    onSecondary = Teal100,
    background = Azure,
    onSurface = Black,
    onError = Red,
    onBackground = MintGreen,
    surface = BrightYellow
)

val darkColors = darkColors(
    primary = SweetRed,
    primaryVariant = LightGray,
    secondary = DarkYellow,
    secondaryVariant = DarkGray,
    onPrimary = DeepOrange400,
    onSecondary = Teal100,
    background = Azure,
    onSurface = White,
    onError = Red,
    onBackground = MintGreen,
    surface = BrightYellow
)

@Composable
fun RoomyMainTheme(
    content: @Composable () -> Unit
) {
    val isDarkTheme: Boolean = isSystemInDarkTheme()
    MaterialTheme(
        colors = if (isDarkTheme) darkColors else brightColors,
        typography = Typography,
        shapes = RoomyShapes,
        content = content
    )
}