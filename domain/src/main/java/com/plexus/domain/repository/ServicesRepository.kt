package com.plexus.domain.repository

import com.plexus.domain.model.response.BaseResponse
import com.plexus.domain.model.response.CharactersResponse
import io.reactivex.Single

interface ServicesRepository {

    fun getAllCharacters(offset: Int = 0): Single<BaseResponse<CharactersResponse>>
    fun getCharacterDetail(id: Int): Single<BaseResponse<CharactersResponse>>
}