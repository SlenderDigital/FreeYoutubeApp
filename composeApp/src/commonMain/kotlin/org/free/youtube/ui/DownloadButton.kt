package org.free.youtube.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.download_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DownloadButton() {
    // State to control the animation
    var isAnimating by remember { mutableStateOf(false) }

    // Animated colors
    val containerColor by animateColorAsState(
        targetValue = if (isAnimating) YouTubeDownloaderTheme.SurfacePressed else YouTubeDownloaderTheme.SurfaceGlass,
        animationSpec = tween(
            durationMillis = if (isAnimating) 150 else 300,
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            // Reset animation state after the red animation completes
            if (isAnimating) {
                isAnimating = false
            }
        }
    )

    val contentColor by animateColorAsState(
        targetValue = if (isAnimating) YouTubeDownloaderTheme.TextPrimary else YouTubeDownloaderTheme.TextTertiary,
        animationSpec = tween(
            durationMillis = if (isAnimating) 150 else 300,
            easing = FastOutSlowInEasing
        )
    )

    Button(
        onClick = {
            // Trigger the animation
            isAnimating = true
            // TODO: Add your download logic here
        },
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = YouTubeDownloaderTheme.SurfaceGlass,
            disabledContentColor = YouTubeDownloaderTheme.TextSecondary
        ),
//        border = BorderStroke(width = 1.dp, color = borderColor),
        shape = YouTubeDownloaderTheme.Shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(Res.drawable.download_icon),
                contentDescription = "Download",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .alignByBaseline()
            )
            Text(
                text = "Download Video"
            )
        }
    }
}

@Preview
@Composable
fun DownloadButtonPreview() {
    YouTubeDownloaderTheme {
        Surface(modifier = Modifier.padding(30.dp), color = YouTubeDownloaderTheme.BackgroundPrimary) {
            DownloadButton()
        }
    }
}