package org.free.youtube.domain

import kotlinx.coroutines.runBlocking

val test: YtVideoImpl = YtVideoImpl()


fun YtVideoTest(): Unit = runBlocking {
    println(test.getVideoInfo("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
}