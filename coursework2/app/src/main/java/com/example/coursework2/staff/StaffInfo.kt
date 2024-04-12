package com.example.coursework2.staff

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StaffInfo(
    @SerializedName("staffId")
    val staffId: Int,
    @SerializedName("nameRu")
    val nameRu: String,
    @SerializedName("nameEn")
    val nameEn: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("posterUrl")
    val posterUrl: String,
    @SerializedName("professionText")
    val professionText: String,
    @SerializedName("professionKey")
    val professionKey: String
): Parcelable
