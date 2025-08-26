package org.free.youtube.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.settings
import freeyoutube.composeapp.generated.resources.audio
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DownloadOptionsHeader(modifier: Modifier = Modifier) {
    var isAudioEnabled by remember { mutableStateOf(true) }
    var isTitleEnabled by remember { mutableStateOf(false) }

// Determine current theme based on audio enabled state
    val currentButtonTheme = if (isAudioEnabled) {
        YouTubeDownloaderTheme.ButtonActivated
    } else {
        YouTubeDownloaderTheme.ButtonDeactivated
    }

// Animated colors
    val animatedBackgroundColor by animateColorAsState(
        targetValue = currentButtonTheme.containerColor,
        animationSpec = tween(durationMillis = 300),
        label = "background_color"
    )

    val animatedContentColor by animateColorAsState(
        targetValue = currentButtonTheme.contentColor,
        animationSpec = tween(durationMillis = 300),
        label = "content_color"
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LeadingIcon(
                painter = painterResource(Res.drawable.settings),
                description = "Settings",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Download Options",
                style = MaterialTheme.typography.titleMedium,
                color = YouTubeDownloaderTheme.TextPrimary
            )
        }

        // Quality and Audio Section
        Row(
            modifier = Modifier.fillMaxWidth().offset(4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Quality Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Quality",
                    style = MaterialTheme.typography.titleSmall,
                    color = YouTubeDownloaderTheme.TextSecondary,
                    fontSize = 14.sp
                )
                val resolutions = listOf("2160p", "1080p", "720p", "480p")
                ResolutionSelector(
                    onClick = {},
                    content = resolutions,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Audio Column
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Audio",
                    style = MaterialTheme.typography.titleSmall,
                    color = YouTubeDownloaderTheme.TextSecondary,
                    fontSize = 14.sp
                )
                Button(
                    onClick = {
                        isAudioEnabled = !isAudioEnabled
                    },
                    modifier = Modifier.size(48.dp),
                    shape = YouTubeDownloaderTheme.Shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = animatedBackgroundColor,
                        contentColor = animatedContentColor
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isAudioEnabled) {
                            YouTubeDownloaderTheme.RedPrimary
                        } else {
                            YouTubeDownloaderTheme.BorderDefault
                        }
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.audio),
                        contentDescription = "Audio Icon",
                        modifier = Modifier.size(20.dp),
                        tint = animatedContentColor
                    )
                }
            }
        }

        // Title Section
        Column(
            modifier = Modifier.offset(4.dp).animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Title (optional)",
                    style = MaterialTheme.typography.titleSmall,
                    color = YouTubeDownloaderTheme.TextSecondary
                )
                Switch(
                    checked = isTitleEnabled,
                    onCheckedChange = { isTitleEnabled = !isTitleEnabled },
//                    modifier = Modifier.scale(0.7f)
                )
            }

            if(isTitleEnabled) {
                Field(
                    modifier = Modifier.fillMaxWidth(),
                    fieldValue = "",
                    onFieldChange = {},
                    placeholder = "Your custom Title"
                )
            }
        }
    }
}


@Preview
@Composable
fun DownloadOptionsHeaderPreview() {
    YouTubeDownloaderTheme {
        DownloadOptionsHeader(modifier = Modifier.padding(16.dp))
    }
}