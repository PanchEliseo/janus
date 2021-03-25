package com.exam.janus.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManagerFactory {

    fun makeRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}