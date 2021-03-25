package com.exam.janus.api

import retrofit2.HttpException
import retrofit2.Response

open class BaseRepository {

    val service = ApiManagerFactory.makeRetrofitService()
    val responseHandler : ResponseHandler = ResponseHandler()
}
