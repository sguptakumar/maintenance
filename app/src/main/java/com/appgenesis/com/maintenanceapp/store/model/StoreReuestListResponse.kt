package com.appgenesis.com.maintenanceapp.store.model


import com.google.gson.annotations.SerializedName

data class StoreReuestListResponse(
    @SerializedName("request")
    val storeRequest: List<StoreRequest>
)