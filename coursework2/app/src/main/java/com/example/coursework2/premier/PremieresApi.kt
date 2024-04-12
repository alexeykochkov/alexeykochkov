package com.example.coursework2.premier

import com.example.coursework2.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface PremieresApi {
    @Headers("X-API-KEY: ${RetrofitInstance.API_KEY}")

    @GET("api/v2.2/films/premieres")
    suspend fun getInformationPremieres(
        @Query("year") year: Int,
        @Query("month") month: String,
    ): PremirersInfo
}


