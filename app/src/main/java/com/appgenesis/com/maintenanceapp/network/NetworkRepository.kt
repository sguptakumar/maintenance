package com.appgenesis.com.maintenanceapp.network

import android.annotation.SuppressLint
import android.provider.Settings
import com.appgenesis.com.maintenanceapp.model.LoginResponseModel
import com.appgenesis.com.maintenanceapp.model.UserRequest
import rx.Observable
import java.util.*

class NetworkRepository {

    @SuppressLint("HardwareIds")
    private fun getHeaderMap(): Map<String, String> {
        val headerMap: MutableMap<String, String> = HashMap()
        headerMap["Content-Type"] = "application/json"
        headerMap["b2c_app_name"] = "XMLHttpRequest"
        return headerMap
    }
}