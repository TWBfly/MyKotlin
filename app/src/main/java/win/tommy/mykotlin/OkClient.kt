package win.tommy.mykotlin

import okhttp3.OkHttpClient

/**
 * 单列
 */
object OkClient {
    val instance = OkHttpClient()
}