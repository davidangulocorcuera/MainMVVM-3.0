package com.plexus.data.cloud

import android.content.Context
import com.plexus.data.cloud.interceptors.RetrofitClient
import com.plexus.data.cloud.model.BaseResponse
import com.plexus.domain.Character
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("{version}/" + ServicesConstants.CHARACTERS)
    fun getCharacters(
        @Path("version") version: String
    ) : Flowable<BaseResponse<ArrayList<Character>>>

    class RetrofitBuilder{
        var retrofit: Retrofit? = null
        fun create(context: Context): ApiServices? {
            if(retrofit == null) {
                RetrofitClient().getClient(context)?.let {
                    retrofit = Retrofit.Builder()
                        .baseUrl("https://developer.marvel.com")
                        .client(it)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return retrofit?.create(ApiServices::class.java)
        }
    }
}