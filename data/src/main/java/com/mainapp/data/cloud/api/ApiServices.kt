package com.mainapp.data.cloud.api

import com.mainapp.data.BuildConfig.*
import com.mainapp.data.cloud.ServicesConstants
import com.mainapp.domain.model.response.BaseResponse
import com.mainapp.domain.model.response.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * © Class created by David Angulo
 * */


interface ApiServices {

    @GET("{version}/" + ServicesConstants.PUBLIC + "/" + ServicesConstants.CHARACTERS)
    fun getCharacters(
        @Path("version") version: String = ServicesConstants.VERSION_API_V1,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20,
        @Query("ts") ts: Int? = 1,
        @Query("hash") hash: String? = API_HASH,
        @Query("apikey") apikey: String? = API_KEY,
    ): Single<BaseResponse<CharactersResponse>>


    @GET("{version}/" + ServicesConstants.PUBLIC + "/" + ServicesConstants.CHARACTERS + "/" + "{id}")
    fun getCharacterDetail(
        @Path("version") version: String = ServicesConstants.VERSION_API_V1,
        @Path("id") id: Int,
        @Query("ts") ts: Int? = 1,
        @Query("hash") hash: String? = API_HASH,
        @Query("apikey") apikey: String? = API_KEY,
    ): Single<BaseResponse<CharactersResponse>>
}