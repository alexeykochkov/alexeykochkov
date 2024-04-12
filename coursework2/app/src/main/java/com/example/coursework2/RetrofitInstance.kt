package com.example.coursework2

import com.example.coursework2.popular.PopularApi
import com.example.coursework2.premier.PremieresApi
import com.example.coursework2.staff.StaffApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"

class RetrofitInstance {

    //создание ОК ХТТП клиента для добавления логгера
    private val client = OkHttpClient().newBuilder().apply {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        addInterceptor(interceptor)
    }.build()

    companion object {
        const val API_KEY = "748f42d6-f0ca-4a91-b6cd-4d9e4e8d0183"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client) //добавление ОК ХТТП клиент для показа в логах данных о запросах
        .build()


    val premieresApi: PremieresApi =
        retrofit.create(PremieresApi::class.java)

    val popularApi: PopularApi =
        retrofit.create(PopularApi::class.java)

    val staffApi: StaffApi =
        retrofit.create(StaffApi::class.java)

}