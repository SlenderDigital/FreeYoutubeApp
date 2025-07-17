package org.free.youtube.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.painterResource

@Composable
fun DownloadOptionsHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.settings),
            contentDescription = "Settings",
            tint = YouTubeDownloaderTheme.RedPrimary,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = "Download Options",
            style = MaterialTheme.typography.titleMedium,
            color = YouTubeDownloaderTheme.TextPrimary
        )
    }
}