package com.appgenesis.com.maintenanceapp.store.model

import com.google.gson.annotations.SerializedName

data class StoreListResponse(
    @SerializedName("data")
    val  storelist:List<StoreListRequest>
)