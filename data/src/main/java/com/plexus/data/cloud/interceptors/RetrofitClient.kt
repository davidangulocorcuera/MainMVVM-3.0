package com.plexus.data.cloud.interceptors

import android.content.Context
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private var client: OkHttpClient? = null

    fun getClient(context: Context): OkHttpClient?{
        if(client == null){
            client = OkHttpClient.Builder()
                .addInterceptor(HeadersInterceptors(context))
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(120,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }
        return client
    }
}