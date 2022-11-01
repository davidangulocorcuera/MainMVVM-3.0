package com.mainapp.data.cloud.repository

import com.mainapp.data.cloud.api.ApiServices
import com.mainapp.domain.model.response.BaseResponse
import com.mainapp.domain.model.response.CharactersResponse
import com.mainapp.domain.repository.ServicesRepository
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