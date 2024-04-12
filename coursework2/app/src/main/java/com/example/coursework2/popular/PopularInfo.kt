package com.example.coursework2.popular

import com.google.gson.annotations.SerializedName

data class PopularInfo(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("items")
    val items: List<PopularFilms>
)

data class PopularFilms(
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int,
    @SerializedName("nameRu")
    val nameRu: String,
    @SerializedName("nameEn")
    val nameEn: String,
    @SerializedName("nameOriginal")
    val nameOriginal: String,
    @SerializedName("countries")
    val countries: List<Contries>,
    @SerializedName("genres")
    val genres: List<GenresPopular>,
    @SerializedName("ratingKinopoisk")
    val ratingKinopoisk: Double,
    @SerializedName("ratingImbd")
    val ratingImbd: Double,
    @SerializedName("year")
    val year: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("posterUrl")
    val posterUrl: String,
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String,
)

data class Contries(
    @SerializedName("country")
    val country: String,
)

data class GenresPopular(
    @SerializedName("genre")
    val genre: String,
)
