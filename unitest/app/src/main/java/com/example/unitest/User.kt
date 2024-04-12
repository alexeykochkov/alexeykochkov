package com.example.unitest

import android.media.Image
import android.net.Uri
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.net.URL

data class User(
    @SerializedName ("results")
    val results:List<Result>
)

data class Result(
    @SerializedName ("gender")
    val gender: String,
    @SerializedName ("name")
    val name: Name,
    @SerializedName ("picture")
    val picture: Picture
)

data class Name (
    @SerializedName("title")
    val title: String,
    @SerializedName ("first")
    val first: String,
    @SerializedName("last")
    val last: String
)

data class Picture (
    @SerializedName ("large")
    val large: URL,
    @SerializedName("medium")
    val medium: URL,
    @SerializedName("thumbnail")
    val thumbnail: URL,
        )

