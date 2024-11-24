package com.harunbekcan.kmpmoviedbapp.android

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = DarkPrimary,
            secondary = AccentRed,
            surface = DarkSurface,
            background = DarkBackground
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE), // Example light theme primary color
            secondary = Color(0xFF03DAC5), // Secondary color
            surface = Color(0xFFFFFFFF),   // Light surface color
            background = Color(0xFFF0F0F0) // Light background color
        )
    }

    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

val DarkBackground = Color(0xFF16171D)
val DarkSurface = Color(0xFF1C1D23)
val DarkPrimary = Color(0xFF222328)
val AccentRed = Color(0xFFDC003B)
