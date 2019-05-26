package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Uploadimage(
    @SerializedName("message")
    val message: String,
    @SerializedName("path")
    val path:Path

)
data class Path(
    @SerializedName("request_image")
    val request_image:String
)