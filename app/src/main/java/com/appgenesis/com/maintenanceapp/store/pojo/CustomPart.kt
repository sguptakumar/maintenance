package com.appgenesis.com.maintenanceapp.store.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CustomPart : Serializable {

     @SerializedName("id")
     var id: Int? = null
     @SerializedName("maintenance_request_id")
     var maintenanceRequestId: String? = null
     @SerializedName("order_type")
     var orderType: String? = null
     @SerializedName("part_name")
     var partName: String? = null
     @SerializedName("description")
     var description: String? = null
     @SerializedName("quantity")
     var quantity: String? = null
     @SerializedName("need_by_date")
     var needByDate: String? = null
     @SerializedName("file")
     var file: String? = null
     @SerializedName("image")
     var image: String? = null
     @SerializedName("status")
     var status: String? = null
     @SerializedName("created_at")
     var createdAt: String? = null
     @SerializedName("updated_at")
     var updatedAt: String? = null

 }