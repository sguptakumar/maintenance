package com.appgenesis.com.maintenanceapp.technician.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.model.CommentPost
import com.appgenesis.com.maintenanceapp.model.MainResponseModel
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.model.*
import com.appgenesis.com.maintenanceapp.technician.adapter.*
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_technician_management_detail.*
import kotlinx.android.synthetic.main.activity_technician_management_detail.maintenancecustompart_recyclerview
import kotlinx.android.synthetic.main.activity_technician_management_detail.maintenanceorderpart_recyclerview

class TechnicianMaintenanceRequestDetailActivity : AppCompatActivity() {
    internal var pdfUri: Uri? = null
    var path:String?=null
    private var orderpart: ArrayList<OrderPart> = ArrayList()
    protected val partName= ArrayList<com.appgenesis.com.maintenanceapp.operation_manager.model.StandardPart>()
    protected val custompart=ArrayList<CustomPart>()
    private var commentList: ArrayList<Comment> = ArrayList()
    var gallryList : IntArray = intArrayOf(R.drawable.machine_image4,R.drawable.machine_image1,R.drawable.machine_image2,R.drawable.machine_image3,R.drawable.machineimage)
    protected lateinit var orderedAdapter: TechnicianOrederPartAdapter
    protected lateinit var customAdapter: TechnicianCustompartAdapter
    protected lateinit var standardAdapter: TechnicianPartNameAdapter
    private lateinit var myLoader: MyProgressDialog
    protected lateinit var commentAdapter: TechnicianCommentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technician_management_detail)
        val btrp : Button=findViewById(R.id.rp)
        val attachfile=findViewById(R.id.attach_file) as TextView
        val post=findViewById(R.id.post)as TextView
        val requestId = intent.getStringExtra("request_id")
       /* btrp.setOnClickListener {
            val intent = Intent(this, MaintenanceManagerStoreRoomActivity::class.java)
            startActivity(intent);
        }*/
        attachfile.setOnClickListener(View.OnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
                selectPdf()

            }
            else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    9
                )
            }
        })
        post.setOnClickListener(View.OnClickListener {
            when{
                comment.text.isEmpty()->Toast.makeText(this,"Please Enter Comment",Toast.LENGTH_SHORT).show()
                else->{
                    val commentpost= CommentPost(
                        request_id =requestId,
                        comment =comment.text.toString(),
                        file = path

                    )
                    ApiClient.getClient().create(NetworkApi::class.java)
                        .postcomment(Constant.getHeaderMap(), commentpost)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(commmentObserver())

                }


            }


        })
        myLoader = MyProgressDialog(this)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getBreakdownRequestDetails(Constant.getHeaderMap(), requestId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(breakdownRequestObserver())


        commentAdapter = TechnicianCommentAdapter(this, commentList)
        commentrecyclerview.layoutManager = LinearLayoutManager(this!!)
        commentrecyclerview.isNestedScrollingEnabled = true
        galleryrecyclerview.adapter = TechnicianDetailOMGalleryAdapter(this, gallryList)
        galleryrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        galleryrecyclerview.isNestedScrollingEnabled = false

        orderedAdapter= TechnicianOrederPartAdapter(this,orderpart)
        maintenanceorderpart_recyclerview.adapter=orderedAdapter
        maintenanceorderpart_recyclerview.layoutManager=LinearLayoutManager(this!!)
        maintenanceorderpart_recyclerview.isNestedScrollingEnabled=true
        customAdapter= TechnicianCustompartAdapter(this,custompart)
        maintenancecustompart_recyclerview.adapter=customAdapter
        maintenancecustompart_recyclerview.layoutManager=LinearLayoutManager(this!!)
        maintenancecustompart_recyclerview.isNestedScrollingEnabled=true



        standardAdapter = TechnicianPartNameAdapter(this, partName)
        part_namerecyclerview.adapter=standardAdapter
        part_namerecyclerview.layoutManager = LinearLayoutManager(this)


        galleryrecyclerview.adapter = TechnicianDetailOMGalleryAdapter(this, gallryList)
        galleryrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        galleryrecyclerview.isNestedScrollingEnabled = false
    }
    private fun commmentObserver(): Observer<MainResponseModel> {
        return object : Observer<MainResponseModel> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MainResponseModel) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                Toast.makeText(this@TechnicianMaintenanceRequestDetailActivity,"Comment is successfully",Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()

                Toast.makeText(applicationContext, ""+e, Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun breakdownRequestObserver(): Observer<BreakdownRequestResponse> {
        return object : Observer<BreakdownRequestResponse> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(breakdownRequestResponse: BreakdownRequestResponse) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(breakdownRequestResponse))
                requestId.text=String.format("Request ID: %s",breakdownRequestResponse.maintenanceRequest!!.id)
                machineno.text=String.format("Machine NO: %s",breakdownRequestResponse.maintenanceRequest!!.machineNumber)
                comment.text = breakdownRequestResponse.maintenanceRequest!!.requestDescription
                commentdescription.text=breakdownRequestResponse.maintenanceRequest!!.requestDescription
                creat_date.text=String.format("%s %s", breakdownRequestResponse.maintenanceRequest!!.requestDate,breakdownRequestResponse.breakdownRequest!!.requestTime)
                suggestedpart.text=String.format("Suggested Part - %s", breakdownRequestResponse.maintenanceRequest!!.suggestedPart)
                var orderresponse= breakdownRequestResponse.maintenanceRequest!!.orders
                var standardorder=orderresponse!!.standardPart
                var orderpart=orderresponse!!.orderPart
                var custompart=orderresponse!!.customPart
                var comment=breakdownRequestResponse.maintenanceRequest.comments
                standardAdapter.addAllStandardpart(standardorder)
                orderedAdapter.addAllOrderpart(orderpart)
                customAdapter.addAllCustompart(custompart)
                commentAdapter.addAllComment(comment)




            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
                Toast.makeText(this@TechnicianMaintenanceRequestDetailActivity,""+e, Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf()
        }
        else
            Toast.makeText(this,
                "Please provide permission..", Toast.LENGTH_SHORT).show()
    }
    private fun selectPdf() {
        val intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 86)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 86 && resultCode == Activity.RESULT_OK && data != null) {
            pdfUri = data.data
            path=pdfUri!!.path
            //var file:File=File(pdfUri!!.path)
            Toast.makeText(this,""+path,Toast.LENGTH_SHORT).show()



        }

        else {
            Toast.makeText(this, "Please Select a file", Toast.LENGTH_SHORT).show()
        }

    }
}
