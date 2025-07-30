package org.free.youtube.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun LeadingIcon(painter: Painter, description: String, modifier: Modifier) {
    Icon(
        painter = painter,
        contentDescription = description,
        tint = YouTubeDownloaderTheme.RedPrimary,
        modifier = modifier
    )
}