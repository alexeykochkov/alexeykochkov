package com.example.coursework2.serials

import android.os.Parcelable
import com.example.coursework2.premier.Genres
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SerialsInfo(
    @SerializedName("total")
    val total: Int,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("items")
    val items: List<Items>
) : Parcelable


@Parcelize
data class Items(
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int,

    @SerializedName("type")
    val type: String
) : Parcelable

