package com.shivayogi.galaxygaze.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EA),
    secondary = Color(0xFF3700B3),
    tertiary = Color(0xFF03DAC5),
    background = Color(0xFF090421),
    surface = Color(0xFF090421),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFFFDFDFD),
    onSurface = Color(0xFFFDFDFD)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EA),
    secondary = Color(0xFF3700B3),
    tertiary = Color(0xFF03DAC5),
    background = Color(0xFFEEF0F7),
    surface = Color(0xFFEEF0F7),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
)

@Composable
fun GalaxyGazeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    //

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}