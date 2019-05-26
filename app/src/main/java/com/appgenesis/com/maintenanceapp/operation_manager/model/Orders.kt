package com.appgenesis.com.maintenanceapp.operation_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Orders:Serializable {

     @SerializedName("standard_part")
     lateinit var standardPart: List<StandardPart>
     @SerializedName("order_part")
     lateinit var orderPart: List<OrderPart>
     @SerializedName("custom_part")
     lateinit var customPart: List<CustomPart>

 }
