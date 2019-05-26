package com.appgenesis.com.maintenanceapp.store.model

import com.appgenesis.com.maintenanceapp.store.pojo.Request
import com.appgenesis.com.maintenanceapp.store.pojo.StandardPart
import com.google.gson.annotations.SerializedName

class StorerequestdetailResponse(
    @SerializedName("request")
    val request:Request

)