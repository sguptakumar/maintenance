package com.appgenesis.com.maintenanceapp.store.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.appgenesis.com.maintenanceapp.LoginRegistrationActivity
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel
import com.appgenesis.com.maintenanceapp.store.adapter.StoreDashboardAdapter
import com.appgenesis.com.maintenanceapp.technician.adapter.TechnicianDashboardAdapter
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_dashbord_maintanance_manager.*

class StoreDashboardActivity : AppCompatActivity() {
    private var operationManger: ArrayList<DashboardModel> = ArrayList()
    var sharedpreferences: SharedPreferenceUtils = SharedPreferenceUtils.getInstance(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_dashboard)
        var model = DashboardModel(name = "List of Part",coutn = "125")
        var model1 = DashboardModel(name = "Request of part",coutn = "55")
        operationManger.add(model)
        operationManger.add(model1)
        recyclerView.adapter = StoreDashboardAdapter(this,operationManger)
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
