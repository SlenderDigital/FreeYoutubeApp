package org.free.youtube.domain

actual interface YtVideo {
    actual suspend fun fetchVideoInfo(url: String): VideoInfo?
}