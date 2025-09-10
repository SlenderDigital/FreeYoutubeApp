package org.free.youtube.domain

import org.junit.jupiter.api.Assertions.*

class MyAppTest {
    private val ytVideo: YtVideo = provideYtVideo()

    @Test
    fun testGetVideoInfoReturnsValidData() = runTest {
        val testUrl = "https://www.youtube.com/watch?v=YSyIa4jI66c" // a known valid test URL

        val videoInfo: CommonVideoInfo? = ytVideo.getVideoInfo(testUrl)

        assertNotNull(videoInfo, "Expected non-null videoInfo")
        assertTrue(!videoInfo.title.isNullOrEmpty(), "Expected non-empty title")

        println("Video title: ${videoInfo.title}")
        println("Video duration: ${videoInfo.duration}")
    }
}