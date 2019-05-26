package com.appgenesis.com.maintenanceapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.activity.MaintenanceManagerDashbordActivity
import com.appgenesis.com.maintenanceapp.management.activity.ManagementDashboardActivity
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerDashboardActivity
import com.appgenesis.com.maintenanceapp.store.activity.StoreDashboardActivity
import com.appgenesis.com.maintenanceapp.technician.activity.TechnicianDashboardActivity
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils

class SplashScreenActivity : Activity() {
    private val SPLASH_DISPLAY_LENGTH = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var sharedpreferences: SharedPreferenceUtils = SharedPreferenceUtils.getInstance(this)
        Handler().postDelayed({
            try {
                val registered = sharedpreferences!!.getBoolanValue("Registered",false)
                val userRole = sharedpreferences!!.getStringValue("UserRole","")

                Log.d("UserRole : ",userRole);
                Log.d("registered :", registered.toString());

                if (registered) {
                    when(userRole){
                        "Admin"->startActivity(Intent(applicationContext, ManagementDashboardActivity::class.java))
                        "OperationManager"->startActivity(Intent(applicationContext, OperationManagerDashboardActivity::class.java))
                        "MaintenanceManager"->startActivity(Intent(applicationContext, MaintenanceManagerDashbordActivity::class.java))
                        "Store"->startActivity(Intent(applicationContext, StoreDashboardActivity::class.java))
                        "Technician"->startActivity(Intent(applicationContext, TechnicianDashboardActivity::class.java))

                    }
                    } else {

                    val intent = Intent(this@SplashScreenActivity, LoginRegistrationActivity::class.java)
                    startActivity(intent)
                    finish()
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
