package com.appgenesis.com.maintenanceapp.store.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Orders (

    @SerializedName("standard_part")
    var standardPart: List<StandardPart>,
    @SerializedName("order_part")
    var orderPart: List<OrderPart>? = null,
    @SerializedName("custom_part")
    var customPart: List<CustomPart>? = null

)
