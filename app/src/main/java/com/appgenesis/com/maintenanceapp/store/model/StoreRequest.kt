package com.appgenesis.com.maintenanceapp.store.model

import com.google.gson.annotations.SerializedName

data class StoreRequest(
    @SerializedName("id")
    val id:Int,
    @SerializedName("user_id")
    val user_id:Int,
    @SerializedName("request_type")
    val request_type:String,
    @SerializedName("machinenumber")
    val machinenumber:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("request_date")
    val request_date:String,
    @SerializedName("request_time")
    val request_time: String,
    @SerializedName("suggested_part")
    val suggested_part:String,
    @SerializedName("request_description")
    val request_description:String,
    @SerializedName("request_image")
    val request_image:String,
    @SerializedName("priority")
    val priority:String,
    @SerializedName("request_status")
    val request_status:String,
    @SerializedName("maintenance_mgr_id")
    val maintenance_mgr_id:String,
    @SerializedName("technician_id")
    val technician_id:Int,
    @SerializedName("created_at")
    val created_at:String,
    @SerializedName("updated_at")
    val updated_at:String
)