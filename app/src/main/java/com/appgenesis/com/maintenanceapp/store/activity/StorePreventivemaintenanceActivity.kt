package com.appgenesis.com.maintenanceapp.store.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.WeeklyBreakDownFragmentOperationManager
import com.appgenesis.com.maintenanceapp.technician.fragment.MonthlyStoreFragment
import com.appgenesis.com.maintenanceapp.technician.fragment.TodayStoreFragment

import kotlinx.android.synthetic.main.activity_breal_down.*

class StorePreventivemaintenanceActivity : AppCompatActivity() {
    var currentFragment: Fragment? = null
    var tag: String? = null
    var currentTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_preventivemaintenance)
        supportActionBar!!.elevation = 0f

        val intent1 = intent.extras

        if(intent1!=null){
            val type = intent1.getString("type")
            if(type == "preventive"){
                supportActionBar!!.title = "Preventive Breakdown"
            }
        }
        val  bundle = Bundle()
        bundle.putString("type","completed")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.parentView, TodayStoreFragment.newInstance(bundle),
                TodayStoreFragment::class.java.name)
            .commit()
        today.setOnClickListener {

            today.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            todayview.visibility = View.VISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE
            val  bundle = Bundle()
//            bundle.putString("id",userData!!.id)
//            bundle.putString("from","newRegistration")
//            bundle.putParcelable("data",userData)
            //bundle.putBoolean("isAdvertise",true)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, TodayStoreFragment.newInstance(bundle),
                    TodayStoreFragment::class.java.name)
                .commit()
        }
        weekly.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, WeeklyStoreFragment.newInstance(bundle),
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
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, MonthlyStoreFragment.newInstance(bundle),
                    MonthlyStoreFragment::class.java.name)
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
