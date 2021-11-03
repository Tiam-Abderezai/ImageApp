package com.example.imageapp.data.model
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

////@Parcelize
//data class Image(
////    @SerializedName("id")
//    var imageUrl: String,
//)
//
//
data class Image(
    @field:SerializedName("url")
    val url: String? = null,
    @field:SerializedName("id")
    val id: String? = null,
)