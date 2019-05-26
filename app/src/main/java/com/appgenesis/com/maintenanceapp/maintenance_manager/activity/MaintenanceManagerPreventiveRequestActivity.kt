package com.appgenesis.com.maintenanceapp.maintenance_manager.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.fragment.AllBreakDownFragmentMaintenanceManager
import com.appgenesis.com.maintenanceapp.maintenance_manager.fragment.AssignedBreakDownFragmentMaintenanceManager
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakDownList
import kotlinx.android.synthetic.main.activity_maintenance_management_break_down_request2.*

class MaintenanceManagerPreventiveRequestActivity : AppCompatActivity() {
    var currentFragment: Fragment? = null
    var tag: String? = null
    var currentTitle: String? = null
    private var type: String? = null
    private var operationManger: ArrayList<BreakDownList> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_manager_preventive_request)
        val intent1 = intent.extras
        type = intent1.getString("type")

        val  bundle = Bundle()
        bundle.putString("type",type)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.parentView, AllBreakDownFragmentMaintenanceManager.newInstance(bundle),
                AllBreakDownFragmentMaintenanceManager::class.java.name)
            .commit()
        var model = BreakDownList(title = "Breakdown Request")
        var model1 = BreakDownList(title = "Breakdown Request")
        var model2 = BreakDownList(title = "Breakdown Request")
        var model3 = BreakDownList(title = "Breakdown Request")
        var model4 = BreakDownList(title = "Breakdown Request")

        operationManger.add(model)
        operationManger.add(model1)
        operationManger.add(model2)
        operationManger.add(model3)
        operationManger.add(model4)

        //Text color change with underline code
        all.setOnClickListener {

            all.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            allview.visibility = View.VISIBLE
            assigned.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            assignedview.visibility = View.INVISIBLE
            // monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            // monthlyview.visibility = View.INVISIBLE
            val  bundle = Bundle()
            bundle.putString("type",type)
//            bundle.putString("id",userData!!.id)
//            bundle.putString("from","newRegistration")
//            bundle.putParcelable("data",userData)
            //bundle.putBoolean("isAdvertise",true)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, AllBreakDownFragmentMaintenanceManager.newInstance(bundle),
                    AllBreakDownFragmentMaintenanceManager::class.java.name)
                .commit()
        }
        assigned.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("type",type)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, AssignedBreakDownFragmentMaintenanceManager.newInstance(bundle),
                    AssignedBreakDownFragmentMaintenanceManager::class.java.name)
                .commit()
            all.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            allview.visibility = View.INVISIBLE
            assigned.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            assignedview.visibility = View.VISIBLE
            // monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            //  monthlyview.visibility = View.INVISIBLE


        }

    }


}
