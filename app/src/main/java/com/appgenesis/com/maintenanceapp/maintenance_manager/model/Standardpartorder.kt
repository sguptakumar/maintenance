package com.appgenesis.com.maintenanceapp.maintenance_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Standardpartorder {

    @SerializedName("request_id")
    var requestId: String? = null
    @SerializedName("standard_items")
    var standardItems: List<StandardItem>? = null
    @SerializedName("custom_items")
    var customItems: List<CustomItem>? = null

}
