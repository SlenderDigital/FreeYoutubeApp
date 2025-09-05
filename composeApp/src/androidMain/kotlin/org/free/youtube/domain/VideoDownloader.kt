package org.free.youtube.domain

import android.app.Application
import kotlin.coroutines.suspendCoroutine
import com.farimarwat.library.YoutubeDL
import timber.log.Timber
import kotlin.coroutines.resume
import com.farimarwat.commons.VideoInfo


var youtubeDl: YoutubeDL? = null

class MyApp : Application() {

    companion object {
        var youtubeDl: YoutubeDL? = null
    }

    override fun onCreate() {
        super.onCreate()

        YoutubeDL.init(
            appContext = this,
            withFfmpeg = true,
            withAria2c = false,
            onSuccess = {
                youtubeDl = it
                Timber.i("YoutubeDL initialized successfully.")
            },
            onError = {
                Timber.e(it, "YoutubeDL initialization failed.")
            }
        )
    }
}

// translating android Class to common Class

fun VideoInfo.toCommonVideoInfo(): CommonVideoInfo {
    return CommonVideoInfo(
        title = this.title,
        duration = this.duration,
        description = this.description,
        thumbnailUrl = this.thumbnail,
        videoUrl = this.url,
        audio = true,
        resolution = this.resolution,
        fileSizeAproximate = this.fileSize
    )
}



actual interface YtVideo {
    actual suspend fun getVideoInfo(url: String): CommonVideoInfo?
}

class YtVideoImpl : YtVideo {
    override suspend fun getVideoInfo(url: String): CommonVideoInfo? = suspendCoroutine { cont ->
        val ytdl = MyApp.youtubeDl ?: run {
            cont.resume(null)
            return@suspendCoroutine
        }

        ytdl.getInfo(
            url = url,
            onSuccess = { videoInfo ->
                cont.resume(videoInfo.toCommonVideoInfo())
            },
            onError = { error ->
                cont.resume(null)
            }
        )
    }
}

