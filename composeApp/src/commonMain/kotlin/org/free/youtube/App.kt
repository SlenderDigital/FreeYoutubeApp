package org.free.youtube

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.icon
import io.ktor.sse.SPACE
import org.free.youtube.domain.YtVideo
import org.free.youtube.ui.DownloadButton
import org.free.youtube.ui.DownloadOptionsHeader
import org.free.youtube.ui.Header
import org.free.youtube.ui.VideoPrevisualizer
import org.free.youtube.ui.YouTubeDownloaderTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    YouTubeDownloaderTheme {


        LazyColumn(
            modifier = Modifier
                .background(YouTubeDownloaderTheme.BackgroundGradient)
                .safeContentPadding()
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        Icon(
                            painter = painterResource(Res.drawable.icon),
                            contentDescription = null,
                            tint = YouTubeDownloaderTheme.RedLight,
                            modifier = Modifier
                                .size(100.dp)
                                .blur(10.dp)
                                .alpha(0.4f)
                        )

                        Icon(
                            painter = painterResource(Res.drawable.icon),
                            contentDescription = "Free Youtube Icon",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Header("Free", "YouTube")
                    Text(
                        text = "Download your favorite videos with ease",
                        style = MaterialTheme.typography.bodyLarge,
                        color = YouTubeDownloaderTheme.TextSecondary,
                    )
                }
            }
            item {
                VideoPrevisualizer(modifier = Modifier.fillMaxWidth())
            }
            item {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                    DownloadOptionsHeader(modifier = Modifier.fillMaxWidth())
                }
            }
            item {
                DownloadButton()
            }
        }
    }
}