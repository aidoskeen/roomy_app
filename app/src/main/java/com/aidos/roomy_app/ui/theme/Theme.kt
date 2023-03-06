package com.aidos.roomy_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val colors = lightColors(
    primary = SweetRed,
    primaryVariant = RedA100,
    secondary = Orange,
    secondaryVariant = Tangerine,
    onPrimary = White,
    onSecondary = MintGreen,
    background = Azure,
    onSurface = Red,
    onError = Red
)

@Composable
fun RoomyMainTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = RoomyShapes,
        content = content
    )
}