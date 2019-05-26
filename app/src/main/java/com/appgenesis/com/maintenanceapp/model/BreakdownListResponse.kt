package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class BreakdownListResponse(
    @SerializedName("breakdownRequests")
    val breakdownRequests: List<BreakdownRequest>,
    @SerializedName("preventiveRequests")
    val preventiveRequests: List<BreakdownRequest>,
    @SerializedName("maintenanceRequests")
    val maintenanceRequests:List<BreakdownRequest>
)