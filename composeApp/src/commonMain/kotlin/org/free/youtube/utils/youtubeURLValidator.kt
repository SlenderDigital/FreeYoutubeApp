package org.free.youtube.utils
object YouTubeUrlValidator {

    // Regular expression patterns for different YouTube URL formats
    private val YOUTUBE_URL_PATTERNS = listOf(
        // Standard YouTube URLs
        "^https?://(www\\.)?youtube\\.com/watch\\?v=([a-zA-Z0-9_-]{11}).*$",
        // YouTube short URLs
        "^https?://youtu\\.be/([a-zA-Z0-9_-]{11}).*$",
        // YouTube mobile URLs
        "^https?://m\\.youtube\\.com/watch\\?v=([a-zA-Z0-9_-]{11}).*$",
        // YouTube embedded URLs
        "^https?://(www\\.)?youtube\\.com/embed/([a-zA-Z0-9_-]{11}).*$",
        // YouTube playlist URLs (with video)
        "^https?://(www\\.)?youtube\\.com/watch\\?v=([a-zA-Z0-9_-]{11})&list=.*$",
        // YouTube time-stamped URLs
        "^https?://(www\\.)?youtube\\.com/watch\\?v=([a-zA-Z0-9_-]{11})&t=.*$",
        // YouTube Shorts URLs
        "^https?://(www\\.)?youtube\\.com/shorts/([a-zA-Z0-9_-]{11}).*$"
    )

    private val compiledPatterns = YOUTUBE_URL_PATTERNS.map { Regex(it) }

    /**
     * Checks if the given URL is a valid YouTube URL
     * @param url The URL string to validate
     * @return true if the URL is a valid YouTube URL, false otherwise
     */
    fun isYouTubeUrl(url: String?): Boolean {
        if (url.isNullOrBlank()) return false

        val trimmedUrl = url.trim()

        return compiledPatterns.any { regex ->
            regex.matches(trimmedUrl)
        }
    }

    /**
     * Extracts the video ID from a YouTube URL
     * @param url The YouTube URL
     * @return The video ID if found, null otherwise
     */
    fun extractVideoId(url: String?): String? {
        if (url.isNullOrBlank()) return null

        val trimmedUrl = url.trim()

        compiledPatterns.forEach { regex ->
            val matchResult = regex.find(trimmedUrl)
            if (matchResult != null) {
                // Find the first non-empty group that contains the video ID
                for (i in 1 until matchResult.groupValues.size) {
                    val group = matchResult.groupValues[i]
                    if (group.isNotEmpty() && group.matches(Regex("[a-zA-Z0-9_-]{11}"))) {
                        return group
                    }
                }
            }
        }

        return null
    }

    /**
     * Validates and normalizes a YouTube URL to standard format
     * @param url The YouTube URL to normalize
     * @return Normalized YouTube URL or null if invalid
     */
    fun normalizeYouTubeUrl(url: String?): String? {
        val videoId = extractVideoId(url) ?: return null
        return "https://www.youtube.com/watch?v=$videoId"
    }

    /**
     * Checks if URL is a YouTube Shorts URL specifically
     * @param url The URL to check
     * @return true if it's a YouTube Shorts URL
     */
    fun isYouTubeShortsUrl(url: String?): Boolean {
        if (url.isNullOrBlank()) return false

        val shortsPattern = Regex("^https?://(www\\.)?youtube\\.com/shorts/([a-zA-Z0-9_-]{11}).*$")
        return shortsPattern.matches(url.trim())
    }

    /**
     * Gets the YouTube domain from URL
     * @param url The YouTube URL
     * @return The domain (youtube.com, youtu.be, etc.) or null if not a YouTube URL
     */
    fun getYouTubeDomain(url: String?): String? {
        if (!isYouTubeUrl(url)) return null

        return when {
            url!!.contains("youtu.be") -> "youtu.be"
            url.contains("m.youtube.com") -> "m.youtube.com"
            else -> "youtube.com"
        }
    }

    /**
     * Alternative validation method using URL parsing (more flexible but less strict)
     * @param url The URL to validate
     * @return true if it appears to be a YouTube URL
     */
    fun isYouTubeUrlLenient(url: String?): Boolean {
        if (url.isNullOrBlank()) return false

        val trimmedUrl = url.trim().lowercase()

        val youtubeHosts = listOf(
            "youtube.com",
            "www.youtube.com",
            "m.youtube.com",
            "youtu.be"
        )

        return youtubeHosts.any { host ->
            trimmedUrl.contains(host) && (
                    trimmedUrl.contains("watch?v=") ||
                            trimmedUrl.contains("embed/") ||
                            trimmedUrl.contains("shorts/") ||
                            trimmedUrl.startsWith("https://youtu.be/") ||
                            trimmedUrl.startsWith("http://youtu.be/")
                    )
        }
    }
}

// Extension functions for easier usage
fun String?.isYouTubeUrl(): Boolean = YouTubeUrlValidator.isYouTubeUrl(this)
fun String?.extractYouTubeVideoId(): String? = YouTubeUrlValidator.extractVideoId(this)
fun String?.normalizeYouTubeUrl(): String? = YouTubeUrlValidator.normalizeYouTubeUrl(this)
fun String?.isYouTubeShortsUrl(): Boolean = YouTubeUrlValidator.isYouTubeShortsUrl(this)