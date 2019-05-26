package com.appgenesis.com.maintenanceapp

import android.annotation.SuppressLint
import android.app.Application
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils

class MyApplication : Application() {
    companion object {
        var instance: MyApplication? = null
        lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this)
    }

}