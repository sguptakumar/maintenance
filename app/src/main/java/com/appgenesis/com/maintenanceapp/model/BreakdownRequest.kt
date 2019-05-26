package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class BreakdownRequest(
    @SerializedName("comments")
    val comments: List<Any>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("machine_number")
    val machineNumber: String,
    @SerializedName("maintenance_mgr_id")
    val maintenanceMgrId: Any,
    @SerializedName("orders")
    val orders: Orders,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("request_date")
    val requestDate: String,
    @SerializedName("request_description")
    val requestDescription: String,
    @SerializedName("request_image")
    val requestImage: String,
    @SerializedName("request_status")
    val requestStatus: String,
    @SerializedName("request_time")
    val requestTime: String,
    @SerializedName("request_type")
    val requestType: String,
    @SerializedName("suggested_part")
    val suggestedPart: String,
    @SerializedName("technician_id")
    val technicianId: Any,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)