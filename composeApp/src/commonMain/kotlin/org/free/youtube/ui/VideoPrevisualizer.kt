package org.free.youtube.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.trash
import org.free.youtube.domain.CommonVideoInfo
import org.free.youtube.domain.YtVideo
import org.free.youtube.domain.provideYtVideo
import org.free.youtube.utils.extractYouTubeVideoId
import org.free.youtube.utils.isYouTubeUrl
import org.jetbrains.compose.resources.painterResource

private val logger = Logger.withTag("VideoPrevisualizer")

@Composable
fun VideoPrevisualizer(modifier: Modifier) {
    var ytURL by remember { mutableStateOf("") }
    val ytVideo = remember { provideYtVideo() }

    var videoInfo by remember { mutableStateOf<CommonVideoInfo?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // State management for delete button
    var isDeletePressed by remember { mutableStateOf(false) }

// Animated colors for delete button
    val animatedDeleteBackgroundColor by animateColorAsState(
        targetValue = if (isDeletePressed) {
            YouTubeDownloaderTheme.RedPrimary
        } else {
            YouTubeDownloaderTheme.BackgroundTertiary
        },
        animationSpec = tween(durationMillis = 300),
        label = "delete_background_color"
    )

    val animatedDeleteIconColor by animateColorAsState(
        targetValue = if (isDeletePressed) {
            YouTubeDownloaderTheme.TextPrimary
        } else {
            YouTubeDownloaderTheme.RedPrimary
        },
        animationSpec = tween(durationMillis = 300),
        label = "delete_icon_color"
    )

    LaunchedEffect(ytURL) {
        logger.d { "LaunchedEffect triggered with URL: $ytURL" }
        if (ytURL.isNotBlank() && ytURL.isYouTubeUrl()) {
            try {
                logger.d { "Fetching video info for URL: $ytURL" }
                videoInfo = ytVideo.getVideoInfo(ytURL)
                errorMessage = null
                logger.d { "Received video title: ${videoInfo?.title}" }
            } catch (e: Exception) {
                errorMessage = e.message
                videoInfo = null
                logger.e(e) { "Error fetching video info" }
            }
        } else {
            videoInfo = null
            errorMessage = null
            logger.d { "Invalid or blank URL, resetting states" }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.animateContentSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Field(
                fieldValue = ytURL,
                onFieldChange = { ytURL = it },
                modifier = Modifier.animateContentSize().weight(1f),
                placeholder = "Paste YouTube URL here..."
            )

            if(ytURL.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(animatedDeleteBackgroundColor)
                        .clickable(
                            enabled = true,
                            onClick = {
                                isDeletePressed = true
                                ytURL=""
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.trash),
                        contentDescription = "Delete",
                        modifier = Modifier.size(20.dp),
                        tint = animatedDeleteIconColor
                    )
                }
            }
        }

        if(ytURL.isYouTubeUrl()) {

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

                    Column {
                        // Use videoInfo safely here
                        if (videoInfo != null) {
                            Text(text = videoInfo?.title ?: "No title")
                            Text(text = "Duration: ${videoInfo?.duration ?: 0} seconds")
                            // etc.
                        } else if (errorMessage != null) {
                            Text(text = "Error: $errorMessage")
                        } else {
                            Text("Loading...")
                        }
                    }
                }
            }
        }
    }
}