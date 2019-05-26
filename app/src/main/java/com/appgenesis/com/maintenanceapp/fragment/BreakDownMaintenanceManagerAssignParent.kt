package com.appgenesis.com.maintenanceapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceMangerClosedRequestAdapter
import com.appgenesis.com.maintenanceapp.model.BreakdownListResponse
import com.appgenesis.com.maintenanceapp.model.BreakdownRequest
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_today_break_down_fragemnt_operation_manager.*

open class BreakDownMaintenanceManagerAssignParent: Fragment() {
    protected lateinit var adapter: MaintenanceMangerClosedRequestAdapter
    protected lateinit var myLoader: MyProgressDialog
    protected val operationManger = ArrayList<BreakdownRequest>()
    private var type: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_today_break_down_fragemnt_operation_manager, container, false)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        type = args!!.getString("type")
        adapter = MaintenanceMangerClosedRequestAdapter(activity, operationManger, this!!.type!!)



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    fun getMaintenanceBreakDownRequestData(filterDate: String,request_status:String) {
        myLoader = MyProgressDialog(this.context!!)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getAssignMaintenanceBreakdownRequest(filterDate,request_status,Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(breakdownListObserver())
    }
    fun getMaintenanceRequestData(filterDate: String,request_status:String) {
        myLoader = MyProgressDialog(this.context!!)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getAssignMaintenanceRequest(filterDate,request_status,Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(maintenanceListObserver())
    }
    fun getPreventiveRequestData(filterDate: String,request_status:String) {
        myLoader = MyProgressDialog(this.context!!)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getAssignPreventiveRequest(filterDate,request_status,Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(preventiveListObserver())
    }
    private fun breakdownListObserver(): Observer<BreakdownListResponse> {
        return object : Observer<BreakdownListResponse> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: BreakdownListResponse) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                if (t != null)
                    adapter.addAllBreakdownRequest(t.breakdownRequests)

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                myLoader.dismiss()
                Toast.makeText(activity,"breakdownlist"+e,Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun preventiveListObserver(): Observer<BreakdownListResponse> {
        return object : Observer<BreakdownListResponse> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: BreakdownListResponse) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                if (t != null)
                    adapter.addAllBreakdownRequest(t.preventiveRequests)

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                myLoader.dismiss()
            }

        }
    }
    private fun maintenanceListObserver(): Observer<BreakdownListResponse> {
        return object : Observer<BreakdownListResponse> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: BreakdownListResponse) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                if (t != null)
                    adapter.addAllBreakdownRequest(t.maintenanceRequests)

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                myLoader.dismiss()
            }

        }
    }

}