package com.example.unitest

import SearchUserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://randomuser.me/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //Библиотека сериализатор который будет преобразовывать входящий json в объет который мы укажем в коллбэке call
//            функции getRandomUser
        .build()

    val searchUserApi: SearchUserApi = retrofit.create(SearchUserApi::class.java) //это создание ретрофит сервиса

}