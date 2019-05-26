package com.appgenesis.com.maintenanceapp.store.model

import com.google.gson.annotations.SerializedName

data class StoreListRequest(
    @SerializedName("id")
    val id:Int,
    @SerializedName("part_name")
    val partname:String,
    @SerializedName("quantity")
    val quantity:String

)