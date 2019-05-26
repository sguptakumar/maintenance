package com.appgenesis.com.maintenanceapp.operation_manager.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.appgenesis.com.maintenanceapp.LoginRegistrationActivity
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.adapter.OperationManagerDashboardAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_main.*

class OperationManagerDashboardActivity : AppCompatActivity() {
    var sharedpreferences: SharedPreferenceUtils = SharedPreferenceUtils.getInstance(this)


    private var operationManger: ArrayList<DashboardModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = DashboardModel(name = "Breakdown Request",coutn = "125")
        val model1 = DashboardModel(name = "Maintenance Request",coutn = "55")
        val model2 = DashboardModel(name = "Preventive Maintenance",coutn = "657")
        val model3 = DashboardModel(name = "Completed Request",coutn = "35")
        val model4 = DashboardModel(name = "Closed Request",coutn = "07")
        operationManger.add(model)
        operationManger.add(model1)
        operationManger.add(model2)
        operationManger.add(model3)
        operationManger.add(model4)

        recyclerView.adapter = OperationManagerDashboardAdapter(this,operationManger)
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
