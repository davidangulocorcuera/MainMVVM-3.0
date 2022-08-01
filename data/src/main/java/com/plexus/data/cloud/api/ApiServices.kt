package com.plexus.data.cloud.api

import android.content.Context
import com.plexus.data.BuildConfig.*
import com.plexus.data.cloud.ServicesConstants
import com.plexus.data.cloud.interceptors.RetrofitClient
import com.plexus.data.cloud.model.BaseResponse
import com.plexus.domain.CharactersResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */


interface ApiServices {

    @GET("{version}/" + ServicesConstants.PUBLIC + "/" + ServicesConstants.CHARACTERS)
    fun getCharacters(
        @Path("version") version: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20,
        @Query("ts") ts: Int? = 1,
        @Query("hash") hash: String? = API_HASH,
        @Query("apikey") apikey: String? = API_KEY,
    ): Flowable<BaseResponse<CharactersResponse>>


    @GET("{version}/" + ServicesConstants.PUBLIC + "/" + ServicesConstants.CHARACTERS + "/" + "{id}")
    fun getCharacterDetail(
        @Path("version") version: String,
        @Path("id") id: Int,
        @Query("ts") ts: Int? = 1,
        @Query("hash") hash: String? = API_HASH,
        @Query("apikey") apikey: String? = API_KEY,
    ): Flowable<BaseResponse<CharactersResponse>>

    class RetrofitBuilder {
        var retrofit: Retrofit? = null
        fun create(context: Context): ApiServices? {
            if (retrofit == null) {
                RetrofitClient().getClient()?.let {
                    retrofit = Retrofit.Builder()
                        .baseUrl(API_URL)
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