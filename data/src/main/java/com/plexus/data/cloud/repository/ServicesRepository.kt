package com.plexus.data.cloud.repository

import com.plexus.data.cloud.api.ApiServices
import com.plexus.data.cloud.ServicesConstants
import com.plexus.data.cloud.model.BaseResponse
import com.plexus.domain.CharactersResponse
import io.reactivex.Flowable
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val apiServices: ApiServices) {

    fun getAllCharacters(
        versionApi: String = ServicesConstants.VERSION_API_V1,
        offset: Int = 0
    ): Flowable<BaseResponse<CharactersResponse>> = apiServices.getCharacters(versionApi, offset)

    fun getCharacterDetail(
        versionApi: String = ServicesConstants.VERSION_API_V1,
        id: Int
    ): Flowable<BaseResponse<CharactersResponse>> = apiServices.getCharacterDetail(versionApi, id)
}