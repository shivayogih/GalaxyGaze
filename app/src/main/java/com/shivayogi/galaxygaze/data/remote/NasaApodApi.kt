package com.shivayogi.galaxygaze.data.remote

import com.shivayogi.galaxygaze.data.datamodels.ApodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApodApi {

    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): Response<ApodResponse>
}