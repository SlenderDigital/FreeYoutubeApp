package org.free.youtube.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

object YouTubeDownloaderTheme {

    // Primary Colors
    val RedPrimary = Color(0xFFEF4444)        // red-500
    val RedSecondary = Color(0xFFDC2626)      // red-600
    val RedDark = Color(0xFFB91C1C)           // red-700
    val RedLight = Color(0xFFF87171)          // red-400

    // Background Colors
    val BackgroundPrimary = Color(0xFF0F172A)  // slate-900
    val BackgroundSecondary = Color(0xFF111827) // gray-900
    val BackgroundTertiary = Color(0xFF1E293B)  // slate-800

    // Surface Colors
    val SurfaceGlass = Color(0x0DFFFFFF)       // white/5
    val SurfaceHover = Color(0x1AFFFFFF)       // white/10
    val SurfacePressed = Color(0x33FFFFFF)     // white/20

    // Border Colors
    val BorderSubtle = Color(0x1AFFFFFF)       // white/10
    val BorderDefault = Color(0x33FFFFFF)      // white/20
    val BorderFocus = Color(0x80EF4444)        // red-500/50

    // Text Colors
    val TextPrimary = Color(0xFFFFFFFF)        // white
    val TextSecondary = Color(0xFF9CA3AF)      // gray-400
    val TextTertiary = Color(0xFFD1D5DB)       // gray-300
    val TextInverted = Color(0xFF000000)         // black
    val TextMuted = Color(0xFF6B7280)          // gray-500

    // Status Colors
    val SuccessGreen = Color(0xFF10B981)       // green-500
    val ErrorRed = Color(0xFFEF4444)           // red-500
    val WarningYellow = Color(0xFFF59E0B)      // yellow-500

    // Shadow Colors
    val ShadowRed = Color(0x33EF4444)          // red-500/20
    val ShadowDefault = Color(0x40000000)      // black/25

    // Gradients
    val BackgroundGradient = Brush.linearGradient(
        colors = listOf(
            BackgroundPrimary,
            BackgroundSecondary,
            BackgroundTertiary
        )
    )

    val RedGradient = Brush.linearGradient(
        colors = listOf(RedPrimary, RedSecondary)
    )

    val RedGradientHover = Brush.linearGradient(
        colors = listOf(RedSecondary, RedDark)
    )

    val GlassGradient = Brush.linearGradient(
        colors = listOf(
            Color(0x0DFFFFFF),
            Color(0x1AFFFFFF)
        )
    )

    // Material3 ColorScheme
    val DarkColorScheme = darkColorScheme(
        primary = RedPrimary,
        onPrimary = Color.White,
        primaryContainer = RedDark,
        onPrimaryContainer = Color.White,
        secondary = RedSecondary,
        onSecondary = Color.White,
        secondaryContainer = RedDark,
        onSecondaryContainer = Color.White,
        tertiary = RedLight,
        onTertiary = Color.Black,
        background = BackgroundPrimary,
        onBackground = TextPrimary,
        surface = BackgroundSecondary,
        onSurface = TextPrimary,
        surfaceVariant = BackgroundTertiary,
        onSurfaceVariant = TextSecondary,
        outline = BorderDefault,
        outlineVariant = BorderSubtle,
        error = ErrorRed,
        onError = Color.White,
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6)
    )

    // Material3 Shapes
    val Shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(16.dp)
    )

    // Button Theme
    val ButtonDeactivated = ButtonColors(
        containerColor = BackgroundTertiary,
        contentColor = TextPrimary,
        disabledContainerColor = BackgroundTertiary,
        disabledContentColor = TextMuted
    )

    val ButtonActivated = ButtonColors(
        containerColor = RedPrimary,
        contentColor = TextInverted,
        disabledContentColor = BackgroundTertiary,
        disabledContainerColor = TextMuted

    )


}



@Composable
fun YouTubeDownloaderTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = YouTubeDownloaderTheme.DarkColorScheme,
        shapes = YouTubeDownloaderTheme.Shapes,
        content = content
    )
}