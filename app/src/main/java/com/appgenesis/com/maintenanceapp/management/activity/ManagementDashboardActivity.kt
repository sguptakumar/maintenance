package com.appgenesis.com.maintenanceapp.management.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.adapter.ManagementDashboardAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.activity.AddBreakDownRequestActivity
import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel
import com.appgenesis.com.maintenanceapp.technician.adapter.TechnicianDashboardAdapter
import kotlinx.android.synthetic.main.activity_dashbord_maintanance_manager.*
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import com.appgenesis.com.maintenanceapp.LoginRegistrationActivity
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils


class ManagementDashboardActivity : AppCompatActivity() {
    var sharedpreferences: SharedPreferenceUtils = SharedPreferenceUtils.getInstance(this)

    private var operationManger: ArrayList<DashboardModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_dashboard)
        var model = DashboardModel(name = "Breakdown Request",coutn = "30")
        var model1 = DashboardModel(name = "Maintenance Request",coutn = "20")
        var model2 = DashboardModel(name = "Preventive Maintenance",coutn = "35")
        var model3 = DashboardModel(name = "Technician",coutn = "10")
        var model4 = DashboardModel(name = "Completed Request",coutn = "35")
        var model5 = DashboardModel(name = "List of Available Part",coutn="35")
        var model6 = DashboardModel(name = "Closed Request",coutn="40")
        operationManger.add(model)
        operationManger.add(model1)
        operationManger.add(model2)
        operationManger.add(model3)
        operationManger.add(model4)
        operationManger.add(model5)
        operationManger.add(model6)
        recyclerView.adapter = ManagementDashboardAdapter(this,operationManger)
        recyclerView.layoutManager  = LinearLayoutManager(this)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Inflate menu
        menuInflater.inflate(R.menu.menu_log_out, menu)
        return true
    }

    //Method to Handle Option Item Click

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val selectedItem = item!!.itemId

        when (selectedItem) {
            R.id.logout -> {

                val registered = sharedpreferences!!.removeKey("Registered")

                    val intent = (Intent(this, LoginRegistrationActivity::class.java))
                    startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
