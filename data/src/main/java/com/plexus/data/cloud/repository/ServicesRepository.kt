package com.plexus.data.cloud.repository

import android.content.Context
import com.plexus.data.cloud.ApiServices
import com.plexus.data.cloud.ServicesConstants
import com.plexus.data.cloud.model.BaseResponse
import com.plexus.domain.Character
import io.reactivex.Flowable

class ServicesRepository(context: Context) {
    private var api: ApiServices? = null

    init {
       api =  ApiServices.RetrofitBuilder().create(context)
    }

    fun getAllCharacters(versionApi: String = ServicesConstants.VERSION_API_V1): Flowable<BaseResponse<ArrayList<Character>>>? = api?.getCharacters(versionApi)
}