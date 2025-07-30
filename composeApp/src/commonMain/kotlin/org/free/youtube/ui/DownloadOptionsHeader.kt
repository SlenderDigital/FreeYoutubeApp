package org.free.youtube.ui

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.settings
import freeyoutube.composeapp.generated.resources.audio
import org.jetbrains.compose.resources.painterResource

@Composable
fun DownloadOptionsHeader(modifier: Modifier = Modifier) {
    var buttonTheme by remember { mutableStateOf(YouTubeDownloaderTheme.ButtonDeactivated) }
    var isAudioEnabled by remember { mutableStateOf(false) }

    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (buttonTheme == YouTubeDownloaderTheme.ButtonActivated) {
            YouTubeDownloaderTheme.ButtonActivated.containerColor
        } else {
            YouTubeDownloaderTheme.ButtonDeactivated.containerColor
        },
        animationSpec = tween(durationMillis = 500),
        label = "background_color"
    )

    val animatedContentColor by animateColorAsState(
        targetValue = if (buttonTheme == YouTubeDownloaderTheme.ButtonActivated) {
            YouTubeDownloaderTheme.ButtonActivated.contentColor
        } else {
            YouTubeDownloaderTheme.ButtonDeactivated.contentColor
        },
        animationSpec = tween(durationMillis = 500),
        label = "content_color"
    )

    Column{
        Row(
            modifier = modifier.padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Quality",
                    style = MaterialTheme.typography.titleSmall,
                    color = YouTubeDownloaderTheme.TextSecondary,
                    fontSize = 14.sp,
                )
                val resolutions = listOf("2160p", "1080p", "720p", "480p")

                ResolutionSelector(
                    onClick = {},
                    content = resolutions,
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(0.25f)
            ) {
                Text(
                    text = "Audio",
                    style = MaterialTheme.typography.titleSmall,
                    color = YouTubeDownloaderTheme.TextSecondary,
                    fontSize = 14.sp,
                )
                Button(
                    onClick = {
                        buttonTheme = when (buttonTheme) {
                            YouTubeDownloaderTheme.ButtonDeactivated -> YouTubeDownloaderTheme.ButtonActivated
                            else -> YouTubeDownloaderTheme.ButtonDeactivated
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = YouTubeDownloaderTheme.Shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = animatedBackgroundColor,
                        contentColor = animatedContentColor
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = YouTubeDownloaderTheme.BorderDefault
                    )
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.audio),
                        contentDescription = "Audio Icon",
                        modifier = Modifier.size(16.dp),
                        tint = YouTubeDownloaderTheme.TextPrimary
                    )
                }
            }
        }
    }
}