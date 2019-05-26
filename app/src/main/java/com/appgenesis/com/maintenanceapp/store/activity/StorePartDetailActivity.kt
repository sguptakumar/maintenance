package com.appgenesis.com.maintenanceapp.store.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.model.CommentList
import com.appgenesis.com.maintenanceapp.store.adapter.*
import com.appgenesis.com.maintenanceapp.store.model.StorerequestdetailResponse
import com.appgenesis.com.maintenanceapp.store.pojo.CustomPart
import com.appgenesis.com.maintenanceapp.store.pojo.StandardPart
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_store_part_detail.*
import java.util.*

class StorePartDetailActivity : AppCompatActivity() {
    protected val orderpart=ArrayList<com.appgenesis.com.maintenanceapp.store.pojo.OrderPart>()
    protected lateinit var adapter: StorePartNameAdapter
    //private var partName: ArrayList<PartName> = ArrayList()
    protected lateinit var orderedAdapter: StoreOrederPartAdapter
    protected lateinit var customAdapter: StoreCustompartAdapter
    protected val partName= ArrayList<StandardPart>()
    var commentList = ArrayList<CommentList>()
    protected val custompart=ArrayList<CustomPart>()
    protected open lateinit var myLoader: MyProgressDialog
    var gallryList : IntArray = intArrayOf(R.drawable.machine_image4,R.drawable.machine_image1,R.drawable.machine_image2,R.drawable.machine_image3,R.drawable.machineimage)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_part_detail)
        val requestId = intent.getStringExtra("request_id")
        commentList.add(CommentList("This "))
        commentList.add(CommentList("This "))
        commentList.add(CommentList("This "))
        orderedAdapter=StoreOrederPartAdapter(this,orderpart)
        orderpart_recyclerview.adapter=orderedAdapter
        orderpart_recyclerview.layoutManager=LinearLayoutManager(this!!)
        orderpart_recyclerview.isNestedScrollingEnabled=true
        customAdapter=StoreCustompartAdapter(this,custompart)
        custompart_recyclerview.adapter=customAdapter
        custompart_recyclerview.layoutManager=LinearLayoutManager(this!!)
        custompart_recyclerview.isNestedScrollingEnabled=true

        commentrecyclerview.adapter = StoreCommentAdapter(this, commentList)
        commentrecyclerview.layoutManager = LinearLayoutManager(this!!)
        commentrecyclerview.isNestedScrollingEnabled = true
        galleryrecyclerview.adapter = StoreDetailOMGalleryAdapter(this, gallryList)
        galleryrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        galleryrecyclerview.isNestedScrollingEnabled = false
        adapter = StorePartNameAdapter(applicationContext,partName)
        part_namerecyclerview.adapter= adapter
        part_namerecyclerview.layoutManager = LinearLayoutManager(applicationContext)
       /* part_namerecyclerview.adapter = StorePartNameAdapter(this, partName)
        part_namerecyclerview.layoutManager = LinearLayoutManager(this)*/

        myLoader = MyProgressDialog(this!!)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getStoreRequestDetail(Constant.getHeaderMap(),requestId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getstoredetailobserver())
    }

    private fun getstoredetailobserver(): Observer<StorerequestdetailResponse> {
        return object : Observer<StorerequestdetailResponse> {

            override fun onNext(storedetailresponse: StorerequestdetailResponse) {
                myLoader.dismiss()
                requestId.text=String.format("Requested Id %d",storedetailresponse.request.id)
                machineno.text= String.format("Machine No %s",storedetailresponse.request.machineNumber)
                comment.text=storedetailresponse.request.requestDescription
                commentdescription.text=storedetailresponse.request.requestDescription
                creat_date.text= String.format("Created At %s %s",storedetailresponse.request.requestDate,storedetailresponse.request.requestTime)
                var response=storedetailresponse.request.orders!!
                var orderpart=response.orderPart
                var custompart=response.customPart
                adapter.addAllstandartpartRequest(response.standardPart)
                orderedAdapter.addAllOrderpart(orderpart)
                customAdapter.addAllCustompart(custompart)
              /*  var orderpart=response.orderPart
                if (orderpart != null) {
                    for (i in orderpart){
                        camshaft1.text=i.partName
                        quantity2.text=i.quantity
                        need_date1.text=i.needByDate
                        lorel_ishpum.text=i.description
                        pending.text=i.status

                    }


                }*/
            }


            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }



            override fun onError(e: Throwable) {
               myLoader.dismiss()
            }

        }
    }
}
