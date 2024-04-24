package com.example.coursework2.serials

import com.example.coursework2.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SerialsApi {
    @Headers("X-API-KEY: ${RetrofitInstance.API_KEY}")

    @GET("api/v2.2/films")
    suspend fun getSerials(
        @Query("genres") genres: ArrayList<Int>,
        @Query("type") type: String,
    ): SerialsInfo
}