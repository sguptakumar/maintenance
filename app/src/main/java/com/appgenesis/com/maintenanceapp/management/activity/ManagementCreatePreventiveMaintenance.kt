package com.appgenesis.com.maintenanceapp.management.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.fragment.ManagementBottomSheetdialogFragment
import com.appgenesis.com.maintenanceapp.management.fragment.MonthlyManagementFragment
import com.appgenesis.com.maintenanceapp.management.fragment.TodayManagementFragment
import com.appgenesis.com.maintenanceapp.management.fragment.WeeklyManagementFragment
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.TodayBreakDownFragemntOperationManager
import com.appgenesis.com.maintenanceapp.operation_manager.fragment.WeeklyBreakDownFragmentOperationManager
import com.appgenesis.com.maintenanceapp.technician.fragment.MonthlyTechnicianFragment
import com.appgenesis.com.maintenanceapp.technician.fragment.TodayTechnicianFragment
import com.appgenesis.com.maintenanceapp.technician.fragment.WeeklyTechnicianFragment
import kotlinx.android.synthetic.main.activity_breal_down.*

class ManagementCreatePreventiveMaintenance : AppCompatActivity(),
    ManagementBottomSheetdialogFragment.BottomSheetListner{
    private var type: String? = null
    override fun onButtonClicked(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_create_preventive_maintenance)
        val intent1 = intent.extras

        if(intent1!=null){
             type = intent1.getString("type")
            if(type == "preventive"){
                supportActionBar!!.title = "Preventive Breakdown"
            }
        }
        val  bundle = Bundle()
        bundle.putString("type",type)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.parentView, TodayManagementFragment.newInstance(bundle),
                TodayManagementFragment::class.java.name)
            .commit()
        today.setOnClickListener {
            val  bundle = Bundle()
            bundle.putString("type",type)

            today.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            todayview.visibility = View.VISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.parentView, TodayManagementFragment.newInstance(bundle),
                    TodayManagementFragment::class.java.name
                )
                .commit()
        }
        weekly.setOnClickListener {
            val  bundle = Bundle()
            bundle.putString("type",type)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, WeeklyManagementFragment.newInstance(bundle),
                    WeeklyManagementFragment::class.java.name)
                .commit()
            today.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            todayview.visibility = View.INVISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            weeklyview.visibility = View.VISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            monthlyview.visibility = View.INVISIBLE

        }
        monthly.setOnClickListener {
            val  bundle = Bundle()
            bundle.putString("type",type)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, MonthlyManagementFragment.newInstance(bundle),
                    MonthlyManagementFragment::class.java.name)
                .commit()
            today.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            todayview.visibility = View.INVISIBLE
            weekly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            weeklyview.visibility = View.INVISIBLE
            monthly.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            monthlyview.visibility = View.VISIBLE

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Inflate menu
        menuInflater.inflate(R.menu.break_dorwn_menu, menu)
        return true
    }

    //Method to Handle Option Item Click

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val selectedItem = item!!.itemId


        when (selectedItem) {
            R.id.add -> {
                 var bottomsheet = ManagementBottomSheetdialogFragment()
                bottomsheet.show(supportFragmentManager, "BottomSheet")


            }
        }
        return super.onOptionsItemSelected(item)
    }
}
