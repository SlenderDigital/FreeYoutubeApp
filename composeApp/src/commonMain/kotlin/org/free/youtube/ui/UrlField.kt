package org.free.youtube.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UrlField(modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { value = it },
        placeholder = {
            Text(
                "Paste YouTube URL here...",
                color = YouTubeDownloaderTheme.TextMuted
            )
        },
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = YouTubeDownloaderTheme.TextPrimary
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = YouTubeDownloaderTheme.BackgroundTertiary,
            unfocusedContainerColor = YouTubeDownloaderTheme.BackgroundTertiary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = YouTubeDownloaderTheme.TextMuted,
            unfocusedPlaceholderColor = YouTubeDownloaderTheme.TextMuted,
            cursorColor = YouTubeDownloaderTheme.RedPrimary,
            focusedTextColor = YouTubeDownloaderTheme.TextPrimary,
            unfocusedTextColor = YouTubeDownloaderTheme.TextPrimary
        ),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = if (isFocused) YouTubeDownloaderTheme.RedPrimary else YouTubeDownloaderTheme.BorderSubtle,
                shape = RoundedCornerShape(12.dp)
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    )
}

