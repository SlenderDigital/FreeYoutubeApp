package org.free.youtube.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import freeyoutube.composeapp.generated.resources.Res
import freeyoutube.composeapp.generated.resources.arrow
import org.jetbrains.compose.resources.painterResource

@Composable
fun ResolutionSelector(
    onClick: (String) -> Unit = {},
    content: List<String>,
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        var expanded by remember { mutableStateOf(false) }
        var resolutionSelected by remember { mutableStateOf("1080p") }

        Button(
            onClick = { expanded = !expanded },
            shape = YouTubeDownloaderTheme.Shapes.medium,
            border = BorderStroke(
                width = 1.dp,
                color = YouTubeDownloaderTheme.BorderDefault
            ),
            colors = ButtonColors(
                containerColor = YouTubeDownloaderTheme.BackgroundTertiary,
                contentColor = YouTubeDownloaderTheme.TextPrimary,
                disabledContainerColor = YouTubeDownloaderTheme.BackgroundTertiary,
                disabledContentColor = YouTubeDownloaderTheme.TextMuted
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp), // Better touch target height
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp) // Better internal padding
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = resolutionSelected,
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow),
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .rotate(if (expanded) 180f else 0f)
                        .size(18.dp) // Slightly larger for better visibility
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, // Close when clicking outside
            border = BorderStroke(width = 1.dp, color = YouTubeDownloaderTheme.BorderDefault),
            shape = YouTubeDownloaderTheme.Shapes.medium,
            containerColor = YouTubeDownloaderTheme.BackgroundTertiary,
            modifier = Modifier.width(IntrinsicSize.Min),
            tonalElevation = 8.dp, // Better elevation for depth
            offset = DpOffset(x = 0.dp, y = 4.dp), // Small offset for better visual separation
            properties = PopupProperties()
        ) {
            content.forEach { resolution ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = resolution,
                            modifier = Modifier.wrapContentWidth()
                        )
                    },
                    colors = MenuItemColors(
                        textColor = YouTubeDownloaderTheme.TextPrimary,
                        disabledTextColor = YouTubeDownloaderTheme.TextMuted,
                        leadingIconColor = YouTubeDownloaderTheme.RedPrimary,
                        disabledLeadingIconColor = YouTubeDownloaderTheme.TextMuted,
                        trailingIconColor = YouTubeDownloaderTheme.RedPrimary,
                        disabledTrailingIconColor = YouTubeDownloaderTheme.TextMuted
                    ),
                    onClick = {
                        onClick(resolution)
                        expanded = false
                        resolutionSelected = resolution
                    },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp), // Better padding
                    modifier = Modifier.width(80.dp) // Slightly wider for better appearance
                )
            }
        }
    }
}