package com.example.coursework2.popular

import com.example.coursework2.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PopularApi {
    @Headers("X-API-KEY: ${RetrofitInstance.API_KEY}")

    @GET("api/v2.2/films/collections")
    suspend fun getInformationPopular(
        @Query("type") type: String,
        @Query("page") page: Int,
    ): PopularInfo
}