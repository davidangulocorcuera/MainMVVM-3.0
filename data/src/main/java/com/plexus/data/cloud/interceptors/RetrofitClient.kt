package com.plexus.data.cloud.interceptors

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class RetrofitClient {
    private var client: OkHttpClient? = null

    fun getClient(): OkHttpClient? {

        val interceptorHeaders = HttpLoggingInterceptor()
        interceptorHeaders.level = HttpLoggingInterceptor.Level.HEADERS

        val interceptorBody = HttpLoggingInterceptor()
        interceptorHeaders.level = HttpLoggingInterceptor.Level.BODY

        if (client == null) {
            client = OkHttpClient.Builder()
                .addInterceptor(interceptorHeaders)
                .addInterceptor(interceptorBody)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }
        return client
    }
}