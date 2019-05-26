package com.appgenesis.com.maintenanceapp.operation_manager.activity


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.model.CommentPost
import com.appgenesis.com.maintenanceapp.model.MainResponseModel
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.adapter.BreakDownDetailOMGalleryAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.adapter.OperationMangerCommentAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakdownRequestResponse
import com.appgenesis.com.maintenanceapp.operation_manager.model.Comment
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_breakdown_detail.*



class OperationManagerMaintenancerequestDetailActivity : AppCompatActivity() {
    internal var pdfUri: Uri? = null
    var path:String?=null
    protected lateinit var commentAdapter: OperationMangerCommentAdapter
    private var commentList: ArrayList<Comment> = ArrayList()

    var gallryList: IntArray = intArrayOf(
        R.drawable.machine_image4,
        R.drawable.machine_image1,
        R.drawable.machine_image2,
        R.drawable.machine_image3,
        R.drawable.machineimage
    )


    private lateinit var myLoader: MyProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakdown_detail)
        val comment=findViewById(R.id.breakdown_detail_et_comment)  as EditText
        val attachfile=findViewById(R.id.breakdown_detail_attach_file) as TextView
        val post=findViewById(R.id.breakdown_detail_post)as TextView
        var requestId = intent.getStringExtra("request_id")
        myLoader = MyProgressDialog(this)
        myLoader.show()

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
                    val commentpost=CommentPost(
                        request_id = requestId,
                        comment =comment.text.toString(),
                        file = path

                    )
                    myLoader = MyProgressDialog(this)
                    myLoader.show()
                    ApiClient.getClient().create(NetworkApi::class.java)
                        .postcomment(Constant.getHeaderMap(), commentpost)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(commmentObserver())

                }


            }


        })


        ApiClient.getClient().create(NetworkApi::class.java)
            .getMaintenanceRequestDetails(Constant.getHeaderMap(), requestId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(breakdownRequestObserver())
        commentAdapter= OperationMangerCommentAdapter(this, commentList)
        breakdown_detail_commentrecyclerview.adapter=commentAdapter
        breakdown_detail_commentrecyclerview.layoutManager = LinearLayoutManager(this)
        breakdown_detail_commentrecyclerview.isNestedScrollingEnabled = true
        breakdown_detail_galleryrecyclerview.adapter = BreakDownDetailOMGalleryAdapter(this, gallryList)
        breakdown_detail_galleryrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        breakdown_detail_galleryrecyclerview.isNestedScrollingEnabled = false

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
                Toast.makeText(this@OperationManagerMaintenancerequestDetailActivity,"Comment is successfully",Toast.LENGTH_SHORT).show()





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

                breakdown_detail_requestId.text = String.format("Request ID: %s",breakdownRequestResponse.maintenanceRequest!!.id)
                breakdown_detail_machineno.text = String.format("Machine NO: %s",breakdownRequestResponse.maintenanceRequest!!.machineNumber)
                breakdown_detail_comment.text = breakdownRequestResponse.maintenanceRequest!!.requestDescription
                breakdown_detail_commentdescription.text = breakdownRequestResponse.maintenanceRequest!!.requestDescription
                breakdown_detail_creat_date.text =String.format("%s %s", breakdownRequestResponse.maintenanceRequest!!.requestDate,breakdownRequestResponse.maintenanceRequest!!.requestTime)
                breakdown_detail_suggestedpart.text =String.format("Suggested Part - %s", breakdownRequestResponse.maintenanceRequest!!.suggestedPart)
                var comment=breakdownRequestResponse.maintenanceRequest.comments
                commentAdapter.addAllComment(comment)
            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
            }

        }
    }



}
