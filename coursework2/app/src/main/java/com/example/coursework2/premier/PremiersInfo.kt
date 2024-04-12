package com.example.coursework2.premier

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PremirersInfo(
    @SerializedName("total")
    val total: Int,
    @SerializedName("items")
    val items: List<PremierFilm>
)

@Parcelize
data class PremierFilm(
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int,
    @SerializedName("nameRu")
    val nameRu: String,
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String,
    @SerializedName("genres")
    val genres: List<Genres>,
) : Parcelable

@Parcelize
data class Genres(
    @SerializedName("genre")
    val genre: String,
) : Parcelable