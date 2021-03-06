package com.appgenesis.com.maintenanceapp.management.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.MonthlyBreakDownFragmentOperationManager
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.TodayBreakDownFragemntOperationManager
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.WeeklyBreakDownFragmentOperationManager
import kotlinx.android.synthetic.main.activity_breal_down.*

class ManagementBreakDownRequestActivity : AppCompatActivity() {
    var currentFragment: Fragment? = null
    var tag: String? = null
    var currentTitle: String? = null
    private var type: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_break_down_request)
        supportActionBar!!.elevation = 0f

        val intent1 = intent.extras

        if (intent1 != null) {
            type = intent1.getString("type")
            if (type == "preventive") {
                supportActionBar!!.title = "Preventive Requests"
            } else if (type == "breakdown") {
                supportActionBar!!.title = "Breakdown Requests"
            }
        }
        val bundle = Bundle()
        bundle.putString("type", type)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.parentView, TodayBreakDownFragemntOperationManager.newInstance(bundle),
                TodayBreakDownFragemntOperationManager::class.java.name
            )
            .commit()
        today.setOnClickListener {

            today.setTextColor(ContextCompat.getColor(this, R.color.activeTextColor))
            todayview.visibility = View.VISIBLE
            weekly.setTextColor(ContextCompat.getColor(this, R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this, R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE

//            bundle.putString("id",userData!!.id)
//            bundle.putString("from","newRegistration")
//            bundle.putParcelable("data",userData)
            //bundle.putBoolean("isAdvertise",true)
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.parentView, TodayBreakDownFragemntOperationManager.newInstance(bundle),
                    TodayBreakDownFragemntOperationManager::class.java.name
                )
                .commit()

        }
        weekly.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.parentView, WeeklyBreakDownFragmentOperationManager.newInstance(bundle),
                    WeeklyBreakDownFragmentOperationManager::class.java.name
                )
                .commit()
            today.setTextColor(ContextCompat.getColor(this, R.color.inactiveTxtColor))
            todayview.visibility = View.INVISIBLE
            weekly.setTextColor(ContextCompat.getColor(this, R.color.activeTextColor))
            weeklyview.visibility = View.VISIBLE
            monthly.setTextColor(ContextCompat.getColor(this, R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE

        }
        monthly.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.parentView, MonthlyBreakDownFragmentOperationManager.newInstance(bundle),
                    MonthlyBreakDownFragmentOperationManager::class.java.name
                )
                .commit()
            today.setTextColor(ContextCompat.getColor(this, R.color.inactiveTxtColor))
            todayview.visibility = View.INVISIBLE
            weekly.setTextColor(ContextCompat.getColor(this, R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this, R.color.activeTextColor))
            monthlyview.visibility = View.VISIBLE

        }
    }
}
