package com.plexus.data.cloud.repository

import com.plexus.data.cloud.api.ApiServices
import com.plexus.domain.model.response.BaseResponse
import com.plexus.domain.model.response.CharactersResponse
import com.plexus.domain.repository.ServicesRepository
import io.reactivex.Single
import javax.inject.Inject

class ServicesRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    ServicesRepository {

    override fun getAllCharacters(
        offset: Int
    ): Single<BaseResponse<CharactersResponse>> = apiServices.getCharacters(offset = offset)

    override fun getCharacterDetail(
        id: Int
    ): Single<BaseResponse<CharactersResponse>> = apiServices.getCharacterDetail(id = id)
}