package com.plexus.data.cloud.interceptors

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private var client: OkHttpClient? = null

    fun getClient(context: Context): OkHttpClient?{

        val interceptorHeaders = HttpLoggingInterceptor()
        interceptorHeaders.level = HttpLoggingInterceptor.Level.HEADERS

        val interceptorBody = HttpLoggingInterceptor()
        interceptorHeaders.level = HttpLoggingInterceptor.Level.BODY

        if(client == null){
            client = OkHttpClient.Builder()
                .addInterceptor(interceptorHeaders)
                .addInterceptor(interceptorBody)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(120,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }
        return client
    }
}