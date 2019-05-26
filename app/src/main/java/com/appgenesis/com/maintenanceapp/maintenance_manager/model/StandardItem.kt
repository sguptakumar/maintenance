package com.appgenesis.com.maintenanceapp.maintenance_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StandardItem {

    @SerializedName("part_name")
    @Expose
    var partName: String? = null
    @SerializedName("quantity")
    @Expose
    var quantity: String? = null

}
