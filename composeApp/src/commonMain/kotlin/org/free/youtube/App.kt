package org.free.youtube

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.icon
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
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

        ) {
            item {
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
            }
            item {
                Header("Free", "YouTube")
            }
            item {
                Text(
                    text = "Download your favorite videos with ease",
                    style = MaterialTheme.typography.bodyLarge,
                    color = YouTubeDownloaderTheme.TextSecondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                VideoPrevisualizer(modifier = Modifier.fillMaxWidth())
            }
            item {
                Column (horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                    DownloadOptionsHeader(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}