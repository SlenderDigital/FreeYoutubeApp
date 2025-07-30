package org.free.youtube.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    word: String,
    highlightedWord: String,
    modifier: Modifier = Modifier
) {
    val textStyle = MaterialTheme.typography.displayMedium

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = word,
            style = textStyle,
            color = YouTubeDownloaderTheme.TextPrimary,
        )
        Text(
            text = highlightedWord,
            style = textStyle,
            color = YouTubeDownloaderTheme.RedPrimary,
        )
    }
}