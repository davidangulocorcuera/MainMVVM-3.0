package com.plexus.data.cloud.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptors(val context: Context) : Interceptor {
    companion object {
        const val APIKEY = "apikey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.addHeader(APIKEY,"2ed1b007382bd9ebef0949bed42da66a93b74043")
        return chain.proceed(requestBuilder.build())
    }
}