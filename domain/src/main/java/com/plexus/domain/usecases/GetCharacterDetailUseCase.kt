package com.plexus.domain.usecases

import com.plexus.domain.model.response.BaseResponse
import com.plexus.domain.model.response.CharactersResponse
import com.plexus.domain.repository.ServicesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    fun getCharacterDetail(
        id: Int
    ): Single<BaseResponse<CharactersResponse>> = servicesRepository.getCharacterDetail(id)
}