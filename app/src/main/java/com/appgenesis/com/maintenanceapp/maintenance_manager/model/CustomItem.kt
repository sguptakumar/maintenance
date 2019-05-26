package com.appgenesis.com.maintenanceapp.maintenance_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CustomItem {

    @SerializedName("part_name")
    var partName: String? = null
    @SerializedName("quantity")
    var quantity: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("need_by_date")
    var needByDate: String? = null

}
