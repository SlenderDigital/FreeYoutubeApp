package org.free.youtube.domain

expect interface YtVideo {
    suspend fun getVideoInfo(url: String): CommonVideoInfo?
}