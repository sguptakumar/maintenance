package com.appgenesis.com.maintenanceapp.network

import android.annotation.SuppressLint
import com.appgenesis.com.maintenanceapp.MyApplication.Companion.sharedPreferenceUtils
import com.appgenesis.com.maintenanceapp.utils.TOKEN
import java.util.*

class Constant {
    companion object {
        const val BASE_URL = "http://appgenesisinfotech.com/"

        @SuppressLint("HardwareIds")
        internal fun getHeaderMap(): Map<String, String> {
            val headerMap: MutableMap<String, String> = HashMap()
            headerMap["Content-Type"] = "application/json"
            headerMap["X-Requested-With"] = "XMLHttpRequest"
            headerMap["Authorization"] = String.format("Bearer %s", sharedPreferenceUtils.getStringValue(TOKEN, ""))
            return headerMap
        }
    }


}
