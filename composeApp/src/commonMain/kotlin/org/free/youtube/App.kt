package org.free.youtube

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.icon
import org.free.youtube.ui.DownloadOptionsHeader
import org.free.youtube.ui.UrlField
import org.free.youtube.ui.YouTubeDownloaderTheme
import org.free.youtube.utils.shadowWithClipIntersect

@Composable
@Preview
fun App() {
    YouTubeDownloaderTheme {
        LazyColumn(
            modifier = Modifier
                .background(YouTubeDownloaderTheme.BackgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            item {
                Icon(
                    painter = painterResource(Res.drawable.icon),
                    contentDescription = "Free Youtube Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(100.dp)
                        .shadowWithClipIntersect(
                            elevation = 100.dp,
                            ambientColor = YouTubeDownloaderTheme.RedLight,
                            spotColor = Color.Transparent,
                        ),

                    )
            }
            item {
                Row( horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "YouTube",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                    )
                    Text(
                        text = "Downloader",
                        style = MaterialTheme.typography.headlineMedium,
                        color = YouTubeDownloaderTheme.RedPrimary,
                    )
                }
            }
            item {
                Text(
                    text = "Download your favorite videos with ease",
                    style = MaterialTheme.typography.bodyLarge,
                    color = YouTubeDownloaderTheme.TextSecondary,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                )
            }
            item {
                UrlField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .shadowWithClipIntersect(
                            elevation = 8.dp,
                            ambientColor = YouTubeDownloaderTheme.RedLight,
                            spotColor = Color.Transparent,
                        )
                    )
            }
            item {
                Column (horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                    DownloadOptionsHeader(modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
    }
}