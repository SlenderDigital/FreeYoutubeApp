package org.free.youtube.domain

data class CommonVideoInfo(
    var title: String?,
    val description: String?,
    val thumbnailUrl: String?,
    val videoUrl: String?,
    var audio: Boolean = true,
    val duration: Int?, // Duration in seconds
    var resolution: String?, // e.g., "1080p", "720p"
    val fileSizeAproximate: Long?
)