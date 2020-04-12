package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

class HttpClient : OkHttpClient {
    private constructor()

    companion object {
        @JvmStatic
        val INSTANCE: HttpClient = HttpClient()
        @JvmStatic
        private val gson = Gson()

        @JvmStatic
        private fun <T> convert(json: String?, type: Type?): T {
            return gson.fromJson(json, type)
        }
    }

    fun <T> get(path: String?, type: Type?, entityCallback: EntityCallback<T>?) {
        val request = Request.Builder()
                .url("https://api.hencoder.com/${path}")
                .build()
        val call = INSTANCE.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                entityCallback?.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200..299 -> {
                        val json: String? = try {
                            response.body()?.string()
                        } catch (e: Exception) {
                            entityCallback?.onFailure(e.message)
                            return
                        }
                        entityCallback?.onSuccess(convert(json, type))
                    }
                    in 400..499 -> entityCallback?.onFailure("客户端错误")
                    in 500..599 -> entityCallback?.onFailure("服务器错误")
                    else -> entityCallback?.onFailure("未知错误")
                }
            }
        })
    }
}