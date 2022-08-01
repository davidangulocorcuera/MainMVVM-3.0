package com.plexus.data.cloud.repository

import android.content.Context
import com.plexus.data.cloud.api.ApiServices
import com.plexus.data.cloud.ServicesConstants
import com.plexus.data.cloud.model.BaseResponse
import com.plexus.domain.Character
import com.plexus.domain.CharactersResponse
import io.reactivex.Flowable

class ServicesRepository(context: Context) {
    private var api: ApiServices? = null

    init {
       api =  ApiServices.RetrofitBuilder().create(context)
    }

    fun getAllCharacters(versionApi: String = ServicesConstants.VERSION_API_V1): Flowable<BaseResponse<CharactersResponse>>? = api?.getCharacters(versionApi)
    fun getCharacterDetail(versionApi: String = ServicesConstants.VERSION_API_V1,id: Int): Flowable<BaseResponse<CharactersResponse>>? = api?.getCharacterDetail(versionApi,id)
}