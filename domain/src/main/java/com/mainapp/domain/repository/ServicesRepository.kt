package com.mainapp.domain.repository

import com.mainapp.domain.model.response.BaseResponse
import com.mainapp.domain.model.response.CharactersResponse
import io.reactivex.Single

interface ServicesRepository {
    fun getAllCharacters(offset: Int = 0): Single<BaseResponse<CharactersResponse>>
    fun getCharacterDetail(id: Int): Single<BaseResponse<CharactersResponse>>
}