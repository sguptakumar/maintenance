package com.appgenesis.com.maintenanceapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.store.adapter.StoreClosedRequestAdapter
import com.appgenesis.com.maintenanceapp.store.model.StoreRequest
import com.appgenesis.com.maintenanceapp.store.model.StoreReuestListResponse
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_today_break_down_fragemnt_operation_manager.*
import java.util.*

open class StorePreventivemaintenanceParent: Fragment() {
    protected lateinit var adapter: StoreClosedRequestAdapter
    protected open lateinit var myLoader: MyProgressDialog
    protected val operationManger = ArrayList<StoreRequest>()

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

        adapter = StoreClosedRequestAdapter(activity, operationManger, "closed")


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    fun getStoreRequestData() {
        myLoader = MyProgressDialog(this.context!!)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getStoreRequest( Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(storeListObserver())
    }

    private fun storeListObserver(): Observer<StoreReuestListResponse> {
        return object : Observer<StoreReuestListResponse> {
            override fun onNext(t: StoreReuestListResponse) {
                myLoader.dismiss()
                if(t!=null) {
                    adapter.addAllStoreRequest(t.storeRequest)
                }
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                myLoader.dismiss()
            }

        }
    }

}