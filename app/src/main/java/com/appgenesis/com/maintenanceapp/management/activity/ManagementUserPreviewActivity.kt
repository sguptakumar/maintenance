package com.appgenesis.com.maintenanceapp.management.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.adapter.ManagementUserPreviewAdapter
import com.appgenesis.com.maintenanceapp.model.MaintenanceListResponse
import com.appgenesis.com.maintenanceapp.model.TechnicianListResponse
import com.appgenesis.com.maintenanceapp.model.TechnicianRequest
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_user_preview.*
import io.reactivex.schedulers.Schedulers

class ManagementUserPreviewActivity : AppCompatActivity() {
    protected lateinit var adapter: ManagementUserPreviewAdapter
    protected lateinit var myLoader: MyProgressDialog
    protected val operationManger = ArrayList<TechnicianRequest>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar!!.elevation = 0f
        setContentView(R.layout.activity_user_preview)
        myLoader = MyProgressDialog(this)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getAllTechnician(Constant.getHeaderMap())
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(technicianlistObserver())

        adapter= ManagementUserPreviewAdapter(this,operationManger,"closed")


        recyclerview_userpreview.adapter =  adapter
        recyclerview_userpreview.layoutManager = LinearLayoutManager(this)
    }
    private fun technicianlistObserver(): Observer<TechnicianListResponse> {
        return object : Observer<TechnicianListResponse> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: TechnicianListResponse) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                adapter.addAllTechnicianeRequest(t.technicianRequests)

            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
                //Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext,""+e,Toast.LENGTH_SHORT).show()



            }

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
            R.id.add->{
                val intent= (Intent(this, ManagementAdduserActivity::class.java))
                intent.putExtra("type","mrrr")
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
