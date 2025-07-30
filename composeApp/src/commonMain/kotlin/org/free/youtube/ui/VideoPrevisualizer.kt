package org.free.youtube.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.trash
import org.free.youtube.utils.extractYouTubeVideoId
import org.free.youtube.utils.isYouTubeUrl
import org.jetbrains.compose.resources.painterResource

@Composable
fun VideoPrevisualizer(modifier: Modifier) {
    var ytURL by remember { mutableStateOf("") }
    val customEnterTransition = scaleIn(initialScale = 0.8f) +
            expandHorizontally(expandFrom = Alignment.End) +
            fadeIn(initialAlpha = 0.3f)

    val customExitTransition = scaleOut(targetScale = 0.8f) +
            shrinkHorizontally(shrinkTowards = Alignment.End) +
            fadeOut(targetAlpha = 0.3f)

    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            UrlField(
                url = ytURL,
                onYtURLChange = { ytURL = it },
                modifier = Modifier.weight(1f)
            )

            AnimatedVisibility(
                visible = ytURL.isNotEmpty(),
                enter = customEnterTransition,
                exit = customExitTransition
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(YouTubeDownloaderTheme.BackgroundTertiary)
                        .clickable(enabled = true, onClick = { ytURL = "" }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.trash),
                        contentDescription = "Delete",
                        modifier = Modifier.size(20.dp),
                        tint = YouTubeDownloaderTheme.RedPrimary
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = ytURL.isYouTubeUrl()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = YouTubeDownloaderTheme.BackgroundTertiary
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val videoId: String? = ytURL.extractYouTubeVideoId()
                    val thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"

                    AsyncImage(
                        model = thumbnailUrl,
                        contentDescription = "Video thumbnail",
                        modifier = Modifier
                            .width(140.dp)
                            .height(78.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "9 DaVinci Resolve Tricks You Wish You Knew Sooner!",
                            style = MaterialTheme.typography.titleMedium,
                            color = YouTubeDownloaderTheme.TextPrimary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = MaterialTheme.typography.titleMedium.lineHeight
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "00:15:11 - 45/206MB",
                            style = MaterialTheme.typography.bodyMedium,
                            color = YouTubeDownloaderTheme.TextSecondary
                        )
                    }
                }
            }
        }
    }
}