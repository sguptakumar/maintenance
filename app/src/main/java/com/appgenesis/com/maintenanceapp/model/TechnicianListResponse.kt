package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class TechnicianListResponse(
    @SerializedName("technicians")
    val technicianRequests: List<TechnicianRequest>

)