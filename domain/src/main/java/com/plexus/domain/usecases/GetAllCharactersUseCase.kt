package com.plexus.domain.usecases

import com.plexus.domain.model.response.BaseResponse
import com.plexus.domain.model.response.CharactersResponse
import com.plexus.domain.repository.ServicesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository,
) {
    fun getAllCharacters(
        offset: Int = 0
    ): Single<BaseResponse<CharactersResponse>> =
        servicesRepository.getAllCharacters(offset)
}