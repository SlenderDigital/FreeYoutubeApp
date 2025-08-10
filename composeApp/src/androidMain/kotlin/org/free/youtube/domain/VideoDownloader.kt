package org.free.youtube.domain

import android.app.Application
import kotlin.coroutines.suspendCoroutine
import com.farimarwat.library.YoutubeDL
import timber.log.Timber
import kotlin.coroutines.resume


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

//abstract class YtVideo : VideoDownloader {
//
//    override suspend fun getVideoInfo(url: String): VideoInfo? {
//        val dl = MyApp.youtubeDl ?: return null // or throw an error if uninitialized
//
//        return suspendCoroutine { cont ->
//            dl.getInfo(
//                url = url,
//                onSuccess = { videoInfo ->
//                    cont.resume(
//                        VideoInfo(
//                            title = videoInfo.title,
//                            description = videoInfo.description,
//                            duration = videoInfo.duration,
//                            thumbnailUrl = videoInfo.thumbnail,
//                            videoUrl = videoInfo.url,
//                            resolution = videoInfo.resolution ?: "1080p",
//                            fileSizeAproximate = videoInfo.fileSizeApproximate
//                        )
//                    )
//                },
//                onError = {
//                    cont.resume(null)
//                }
//            )
//        }
//    }
//}
