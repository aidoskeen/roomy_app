package com.aidos.roomy_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val brightColors = lightColors(
    primary = SweetRed,
    primaryVariant = RedA100,
    secondary = Amber400,
    secondaryVariant = Tangerine,
    onPrimary = DeepOrange400,
    onSecondary = Teal100,
    background = Azure,
    onSurface = Black,
    onError = Red,
    onBackground = MintGreen,
    surface = Orange
)

val darkColors = darkColors(
    primary = SweetRed,
    primaryVariant = RedA100,
    secondary = Amber400,
    secondaryVariant = Tangerine,
    onPrimary = DeepOrange400,
    onSecondary = Teal100,
    background = Azure,
    onSurface = White,
    onError = Red,
    onBackground = MintGreen,
    surface = Orange
)

@Composable
fun RoomyMainTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = if (isDarkTheme) darkColors else brightColors,
        typography = Typography,
        shapes = RoomyShapes,
        content = content
    )
}