package com.mainapp.domain.usecases.network

import com.mainapp.domain.model.response.BaseResponse
import com.mainapp.domain.model.response.CharactersResponse
import com.mainapp.domain.repository.ServicesRepository
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