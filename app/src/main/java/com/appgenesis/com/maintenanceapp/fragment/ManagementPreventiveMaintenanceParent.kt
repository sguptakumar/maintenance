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
import com.appgenesis.com.maintenanceapp.management.adapter.ManagementClosedRequestAdapter
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

open class ManagementPreventiveMaintenanceParent: Fragment() {
    protected lateinit var adapter: ManagementClosedRequestAdapter
    protected lateinit var myLoader: MyProgressDialog
    protected val operationManger = ArrayList<BreakdownRequest>()
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
        adapter = ManagementClosedRequestAdapter(activity, operationManger, "closed")


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    fun getAllPreventiveRequest(filterDate: String) {
        myLoader = MyProgressDialog(this.context!!)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getAllPreventiveRequest(filterDate, Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(preventiveListObserver())
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
                if(t!=null) {
                    adapter.addAllBreakdownRequest(t.preventiveRequests)
                }


            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                myLoader.dismiss()
                Toast.makeText(activity,""+e, Toast.LENGTH_SHORT).show()
            }

        }
    }

}