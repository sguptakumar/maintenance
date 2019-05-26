package com.appgenesis.com.maintenanceapp.operation_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BreakdownRequestResponse(

    @SerializedName("breakdownRequest")
    var breakdownRequest: BreakdownReq,
    @SerializedName("maintenanceRequest")
    var maintenanceRequest:BreakdownReq,
    @SerializedName("preventiveRequest")
    var preventiveRequest:BreakdownReq
)


