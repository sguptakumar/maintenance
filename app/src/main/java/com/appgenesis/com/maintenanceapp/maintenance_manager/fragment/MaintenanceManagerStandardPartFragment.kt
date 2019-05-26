package com.appgenesis.com.maintenanceapp.maintenance_manager.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceManagerStoreRoomAdapter
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.store.model.StoreListRequest
import com.appgenesis.com.maintenanceapp.store.model.StoreListResponse
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.maintenance_alert_dialog_item.view.*
import kotlinx.android.synthetic.main.maintenancemanagerstandardpart.*





class MaintenanceManagerStandardPartFragment : Fragment() {
    protected lateinit var listpartAdapter: MaintenanceManagerStoreRoomAdapter
    protected open lateinit var myLoader: MyProgressDialog
    val operationManger = ArrayList<StoreListRequest>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.maintenancemanagerstandardpart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* var model = StoreRoom(spart_name = "Breakdown Request",savailable_quantity = "1",required_quantiy = "1")
        var model1 = StoreRoom(spart_name = "Breakdown Request",savailable_quantity = "1",required_quantiy = "1")
        var model2 = StoreRoom(spart_name = "Breakdown Request",savailable_quantity = "1",required_quantiy = "1")
        var model3 =StoreRoom(spart_name = "Breakdown Request",savailable_quantity = "1",required_quantiy = "1")
        var model4 = StoreRoom(spart_name = "Breakdown Request",savailable_quantity = "1",required_quantiy = "1")

        operationManger.add(model)
        operationManger.add(model1)
        operationManger.add(model2)
        operationManger.add(model3)
        operationManger.add(model4)*/
        myLoader = context?.let { MyProgressDialog(it) }!!
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getStorelistpart(Constant.getHeaderMap())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listofpartobserver())

        listpartAdapter = MaintenanceManagerStoreRoomAdapter(activity,operationManger)
        store_roomrecyclerview.adapter=listpartAdapter
        store_roomrecyclerview.layoutManager  = LinearLayoutManager(activity)
        storeroomorderpart.setOnClickListener(View.OnClickListener {

           /* val mDialogView = LayoutInflater.from(activity).inflate(R.layout.maintenance_alert_dialog_item, null);
            //Alert Dialog Builder
            val mBuilder = android.support.v7.app.AlertDialog.Builder(this!!.activity!!).setView(mDialogView).setIcon(R.drawable.cancel)
            val mAlertDialog = mBuilder.show()
            var list = ArrayList<AlertDialog>()
            var item = AlertDialog("", "", "", "")
            var item1 = AlertDialog("", "", "", "")
            var item2 = AlertDialog("", "", "", "")
            var item3 = AlertDialog("", "", "", "")
            list.add(item)
            list.add(item1)
            list.add(item2)
            list.add(item3)
            mDialogView.alert_dialog_recyclerView.adapter = AlertDialogAdapter(this!!.activity!!, list)
            mDialogView. alert_dialog_recyclerView.layoutManager = LinearLayoutManager(activity)
            mDialogView.dialogcancel.setOnClickListener {
                mAlertDialog.dismiss()
            }*/
            val newFragment = ExtrapartFragmentMaintenanceManager()
            val args = Bundle()

            val transaction = activity!!.getSupportFragmentManager().beginTransaction()

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.parentView, newFragment)
            transaction.addToBackStack(null)

// Commit the transaction
            transaction.commit()
        })
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


    companion object {
        fun newInstance(args: Bundle): MaintenanceManagerStandardPartFragment {
            val fragment = MaintenanceManagerStandardPartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}


