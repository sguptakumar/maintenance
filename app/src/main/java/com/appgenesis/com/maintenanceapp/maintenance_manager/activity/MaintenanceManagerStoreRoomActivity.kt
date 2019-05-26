package com.appgenesis.com.maintenanceapp.maintenance_manager.activity
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.fragment.CustomPartFragmentMaintenanceManager
import com.appgenesis.com.maintenanceapp.maintenance_manager.fragment.MaintenanceManagerStandardPartFragment
import kotlinx.android.synthetic.main.activity_maintenance_manager_store_room.*


class MaintenanceManagerStoreRoomActivity : AppCompatActivity() {
    var id :String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_manager_store_room)
        val toolbar:Toolbar=findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setTitle("StoreRoom")
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener { finish() }


        val intent1 = intent.extras
        if (intent1!=null) {
              id = intent1.getString("request_id")

        }

        val  bundle = Bundle()


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.parentView, MaintenanceManagerStandardPartFragment.newInstance(bundle),
                MaintenanceManagerStandardPartFragment::class.java.name)
            .commit()

        standardpart.setOnClickListener(View.OnClickListener {
            standardpart.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            standartpartview.visibility = View.VISIBLE
            scustompart.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            customview.visibility = View.INVISIBLE
            // monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            // monthlyview.visibility = View.INVISIBLE
            val  bundle = Bundle()
//            bundle.putString("id",userData!!.id)
//            bundle.putString("from","newRegistration")
//            bundle.putParcelable("data",userData)
            //bundle.putBoolean("isAdvertise",true)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, MaintenanceManagerStandardPartFragment.newInstance(bundle),
                    MaintenanceManagerStandardPartFragment::class.java.name)
                .commit()
        })
        scustompart.setOnClickListener {
            val  bundle = Bundle()
            bundle.putString("request_id",id)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.parentView, CustomPartFragmentMaintenanceManager.newInstance(bundle),
                    CustomPartFragmentMaintenanceManager::class.java.name)
                .commit()
            standardpart.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            standartpartview.visibility = View.INVISIBLE
            scustompart.setTextColor(ContextCompat.getColor(this,R.color.activeTextColor))
            customview.visibility = View.VISIBLE
            // monthly.setTextColor(ContextCompat.getColor(this,R.color.inactiveTxtColor))
            //  monthlyview.visibility = View.INVISIBLE


        }

    }




}