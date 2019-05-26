package com.appgenesis.com.maintenanceapp.operation_manager.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.BreakDownRequestFragemntOperationManager
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.MonthlyBreakDownFragmentOperationManager
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.TodayBreakDownFragemntOperationManager
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.WeeklyBreakDownFragmentOperationManager
import kotlinx.android.synthetic.main.activity_completed_request_operation_manager.*

class CompletedRequestActivityOperationManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_request_operation_manager)
        supportActionBar!!.elevation = 0f
        val  bundle = Bundle()
        bundle.putString("type","completed")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.parentView, BreakDownRequestFragemntOperationManager.newInstance(bundle),
                BreakDownRequestFragemntOperationManager::class.java.name)
            .commit()

        br.setOnClickListener {

            br.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            brview.visibility = View.VISIBLE
            pm.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            pmview.visibility = View.INVISIBLE
            mr.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            mrview.visibility = View.INVISIBLE
            val  bundle = Bundle()
            bundle.putString("type","completed")

//            bundle.putString("id",userData!!.id)
//            bundle.putString("from","newRegistration")
//            bundle.putParcelable("data",userData)
            //bundle.putBoolean("isAdvertise",true)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, BreakDownRequestFragemntOperationManager.newInstance(bundle),
                    BreakDownRequestFragemntOperationManager::class.java.name)
                .commit()
        }
        pm.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, BreakDownRequestFragemntOperationManager.newInstance(bundle),
                    BreakDownRequestFragemntOperationManager::class.java.name)
                .commit()
            pm.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            pmview.visibility = View.VISIBLE
            br.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            brview.visibility = View.INVISIBLE
            mr.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            mrview.visibility = View.INVISIBLE

        }
        mr.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, BreakDownRequestFragemntOperationManager.newInstance(bundle),
                    BreakDownRequestFragemntOperationManager::class.java.name)
                .commit()
            mr.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            mrview.visibility = View.VISIBLE
            pm.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            pmview.visibility = View.INVISIBLE
            br.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            brview.visibility = View.INVISIBLE

        }
    }
}
