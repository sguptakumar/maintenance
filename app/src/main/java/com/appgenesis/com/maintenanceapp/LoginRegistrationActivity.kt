package com.appgenesis.com.maintenanceapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.appgenesis.com.maintenanceapp.fragment.LoginFragment
import com.appgenesis.com.maintenanceapp.management.activity.ManagementDashboardActivity
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.BreakDownRequestFragemntOperationManager

class LoginRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_registration_activtiy)
        supportActionBar!!.elevation = 0f
        val  bundle = Bundle()
        bundle.putString("type","closed")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.loginmainlaypout, LoginFragment.newInstance(bundle),
                BreakDownRequestFragemntOperationManager::class.java.name)
            .commit()

    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


}
