package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("custom_part")
    val customPart: List<Any>,
    @SerializedName("order_part")
    val orderPart: List<Any>,
    @SerializedName("standard_part")
    val standardPart: List<Any>
)