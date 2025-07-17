package org.free.youtube

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.compose_multiplatform
import freeyoutube.composeapp.generated.resources.icon
import freeyoutube.composeapp.generated.resources.settings
import org.free.youtube.ui.DownloadOptionsHeader
import org.free.youtube.ui.UrlField
import org.free.youtube.ui.YouTubeDownloaderTheme
import org.free.youtube.utils.shadowWithClipIntersect
import kotlin.random.Random

@Composable
@Preview
fun App() {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .background(YouTubeDownloaderTheme.BackgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .padding(horizontal = 16.dp),
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
                Column( horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "YouTube",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White,
                    )
                    Text(
                        text = "Downloader",
                        style = MaterialTheme.typography.displayMedium,
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
                DownloadOptionsHeader()
            }
        }
    }
}