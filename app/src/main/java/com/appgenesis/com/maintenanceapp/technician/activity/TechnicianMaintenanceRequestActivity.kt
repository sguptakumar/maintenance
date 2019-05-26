package com.appgenesis.com.maintenanceapp.technician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.WeeklyBreakDownFragmentOperationManager
import com.appgenesis.com.maintenanceapp.technician.fragment.MonthlyTechnicianFragment
import com.appgenesis.com.maintenanceapp.technician.fragment.TodayTechnicianFragment
import com.appgenesis.com.maintenanceapp.technician.fragment.WeeklyTechnicianFragment
import kotlinx.android.synthetic.main.activity_breal_down.*

class TechnicianMaintenanceRequestActivity : AppCompatActivity() {
    private var type: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technician_maintenance_request)
        supportActionBar!!.elevation = 0f

        val intent1 = intent.extras
        type=intent1.getString("type")

        if(intent1!=null){
            val type = intent1.getString("type")
            if(type == "mr"){
                supportActionBar!!.title = "Maintenance Request"
            }
        }
        val  bundle = Bundle()
        bundle.putString("type",type)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.parentView, TodayTechnicianFragment.newInstance(bundle),
                TodayTechnicianFragment::class.java.name)
            .commit()
        today.setOnClickListener {

            today.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            todayview.visibility = View.VISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE
            val  bundle = Bundle()
            bundle.putString("type", type)

//            bundle.putString("id",userData!!.id)
//            bundle.putString("from","newRegistration")
//            bundle.putParcelable("data",userData)
            //bundle.putBoolean("isAdvertise",true)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, TodayTechnicianFragment.newInstance(bundle),
                    TodayTechnicianFragment::class.java.name)
                .commit()
        }
        weekly.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", type)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, WeeklyTechnicianFragment.newInstance(bundle),
                    WeeklyBreakDownFragmentOperationManager::class.java.name)
                .commit()
            today.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            todayview.visibility = View.INVISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            weeklyview.visibility = View.VISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE

        }
        monthly.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", type)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, MonthlyTechnicianFragment.newInstance(bundle),
                    MonthlyTechnicianFragment::class.java.name)
                .commit()
            today.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            todayview.visibility = View.INVISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            monthlyview.visibility = View.VISIBLE

        }
    }

}
