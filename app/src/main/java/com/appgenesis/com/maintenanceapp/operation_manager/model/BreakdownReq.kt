package com.appgenesis.com.maintenanceapp.operation_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class BreakdownReq(

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("user_id")
    var userId: String? = null,
    @SerializedName("request_type")
    var requestType: String? = null,
    @SerializedName("machine_number")
    var machineNumber: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("request_date")
    var requestDate: String? = null,
    @SerializedName("request_time")
    var requestTime: String? = null,
    @SerializedName("suggested_part")
    var suggestedPart: String? = null,
    @SerializedName("request_description")
    var requestDescription: String? = null,
    @SerializedName("request_image")
    var requestImage: String? = null,
    @SerializedName("request_file")
    var requestFile: Any? = null,
    @SerializedName("priority")
    var priority: String? = null,
    @SerializedName("request_status")
    var requestStatus: String? = null,
    @SerializedName("maintenance_mgr_id")
    var maintenanceMgrId: Any? = null,
    @SerializedName("technician_id")
    var technicianId: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("orders")
    var orders: Orders? = null,
    @SerializedName("comments")
    var comments: List<Comment>? = null

)
