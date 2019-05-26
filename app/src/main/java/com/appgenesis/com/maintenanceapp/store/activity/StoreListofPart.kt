package com.appgenesis.com.maintenanceapp.store.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceMangerPartNameAdapter
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakdownRequestResponse
import com.appgenesis.com.maintenanceapp.operation_manager.model.DashboardModel
import com.appgenesis.com.maintenanceapp.operation_manager.model.OrderPart
import com.appgenesis.com.maintenanceapp.store.adapter.StoreDashboardAdapter
import com.appgenesis.com.maintenanceapp.store.adapter.StoreListofpartadapter
import com.appgenesis.com.maintenanceapp.store.model.StoreListRequest
import com.appgenesis.com.maintenanceapp.store.model.StoreListResponse
import com.appgenesis.com.maintenanceapp.store.model.listofpartmodel
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dashbord_maintanance_manager.*
import kotlinx.android.synthetic.main.activity_maintenance_management_detail.*
import kotlinx.android.synthetic.main.activity_store_listof_part.*

class StoreListofPart : AppCompatActivity() {

    protected lateinit var listpartAdapter: StoreListofpartadapter
    private var listpart: ArrayList<StoreListRequest> = ArrayList()
    protected open lateinit var myLoader: MyProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_listof_part)
        myLoader = MyProgressDialog(this)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getStorelistpart(Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listofpartobserver())
        listpartAdapter = StoreListofpartadapter(this, listpart)
        recycler_listpart.adapter = listpartAdapter
        recycler_listpart.layoutManager = LinearLayoutManager(this)


    }

    private fun listofpartobserver(): Observer<StoreListResponse> {
        return object : Observer<StoreListResponse> {


            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(storelistresponse: StoreListResponse) {
                Log.d("Response", Gson().toJson(storelistresponse))
                listpartAdapter.addAlllistpart(storelistresponse.storelist)
                myLoader.dismiss()


            }
            override fun onError(e: Throwable) {
                myLoader.dismiss()

            }
        }
    }

}
