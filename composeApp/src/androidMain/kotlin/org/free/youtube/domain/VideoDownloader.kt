package org.free.youtube.domain

import android.app.Application
import com.farimarwat.library.YoutubeDL
import com.farimarwat.commons.YoutubeDLRequest
import org.free.youtube.domain.VideoInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import timber.log.Timber

class AndroidYtVideo(private val app: Application) : YtVideo {

    private var youtubeDl: YoutubeDL? = null

    init {
        YoutubeDL.init(
            appContext = app,
            withFfmpeg = true,
            withAria2c = false,
            onSuccess = {
                youtubeDl = it
                Timber.i("YoutubeDL initialized")
            },
            onError = { Timber.e(it) }
        )
    }

    override suspend fun getVideoInfo(url: String): VideoInfo? {
        return suspendCoroutine { continuation ->youtubeDl?.getInfo(
            url = url,
            onSuccess = { videoInfo ->
                println("Title: ${videoInfo.title}")
                println("Duration: ${videoInfo.duration}")
            },
            onError = { error ->
                println("Error: ${error.message}")
            }
        )
    }

}

private lateinit var androidAppInstance: Application

// Setter to assign the Application instance before calling factory function
fun setAndroidAppInstance(app: Application) {
    androidAppInstance = app
}

actual fun createYtVideo(): YtVideo = AndroidYtVideo(androidAppInstance)
