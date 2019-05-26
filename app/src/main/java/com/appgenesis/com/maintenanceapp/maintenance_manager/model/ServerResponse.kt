package com.appgenesis.com.maintenanceapp.maintenance_manager.model
import com.google.gson.annotations.SerializedName


data class ServerResponse (
        @SerializedName("message")
        var message: String? = null,
         @SerializedName("path")
        var path: Path? = null
)
data class Path (
        @SerializedName("request_image")
        var request_image:String? = null

     )