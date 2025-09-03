package org.free.youtube.domain

actual interface YtVideo {
    actual fun getVideoInfo(url: String): VideoInfo?
    actual fun downloadVideo(videoInfo: VideoInfo, outputPath: String): Boolean
}

actual fun createYtVideo(): YtVideo
