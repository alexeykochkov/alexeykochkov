package com.example.coursework2.staff

import com.example.coursework2.RetrofitInstance
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface StaffApi {

        @Headers("X-API-KEY: ${RetrofitInstance.API_KEY}")

        @GET("api/v1/staff")
        suspend fun getInformationStaff(
            @Query("filmId") filmId : Int,
        ): ArrayList<StaffInfo>

}