package org.free.youtube.domain

interface YtVideo {
    suspend fun getVideoInfo(url: String): VideoInfo?
    suspend fun downloadVideo(videoInfo: VideoInfo, outputPath: String): Boolean
}