package com.example.designday.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = White,
    secondary = Rust300,
    background = Black800,
    surface = White150,
    onPrimary = Gray900,
    onSecondary = Gray900,
    onBackground = Taupe100,
    onSurface = White800,
    primaryVariant = White800
)

private val LightColorPalette = lightColors(
    primary = Gray900,
    secondary = Rust600,
    background = Taupe100,
    surface = White850,
    onPrimary = White,
    onSecondary = White,
    onBackground = Taupe800,
    onSurface = Gray800,
    primaryVariant = Black800

)

@Composable
fun DesignDayTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}