package com.appgenesis.com.maintenanceapp.maintenance_manager.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceManagerCustompartAdapter
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceManagerOrederPartAdapter
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceMangerCommentAdapter
import com.appgenesis.com.maintenanceapp.maintenance_manager.adapter.MaintenanceMangerPartNameAdapter
import com.appgenesis.com.maintenanceapp.model.AssignTechnician
import com.appgenesis.com.maintenanceapp.model.CommentPost
import com.appgenesis.com.maintenanceapp.model.MainResponseModel
import com.appgenesis.com.maintenanceapp.model.TechnicianListResponse
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.adapter.BreakDownDetailOMGalleryAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.adapter.OperationMangerCommentAdapter
import com.appgenesis.com.maintenanceapp.operation_manager.model.*
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_maintenance_management_detail.*
import org.json.JSONArray

class MaintenanceManagementBreakdownRequestDetailActivity : AppCompatActivity() {
    internal var pdfUri: Uri? = null
    var path:String?=null
    private var orderpart: ArrayList<OrderPart> = ArrayList()
    protected val partName= ArrayList<com.appgenesis.com.maintenanceapp.operation_manager.model.StandardPart>()
    protected val custompart=ArrayList<CustomPart>()
    protected val technicianlist = ArrayList<String>()
    protected var technicianId = ArrayList<String>()
    protected lateinit var standardAdapter: MaintenanceMangerPartNameAdapter
    protected lateinit var orderedAdapter: MaintenanceManagerOrederPartAdapter
    protected lateinit var customAdapter: MaintenanceManagerCustompartAdapter
    protected lateinit var commentAdapter: MaintenanceMangerCommentAdapter
    var spinner:Spinner?=null
    var technicianid:String?=null
    var c:String?=null
    private var type: String? = null
    private var jsonArray: JSONArray? = null
    var requestId:String?=null

    private var commentList: ArrayList<Comment> = ArrayList()

    var gallryList: IntArray = intArrayOf(
        R.drawable.machine_image4,
        R.drawable.machine_image1,
        R.drawable.machine_image2,
        R.drawable.machine_image3,
        R.drawable.machineimage
    )
    var sharedpreferences: SharedPreferenceUtils? = null


    private lateinit var myLoader: MyProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_management_detail)
        val toolbar: Toolbar =findViewById(R.id.toolbar)
        val comment=findViewById(R.id.breakdown_detail_et_comment)  as EditText
        val post=findViewById(R.id.breakdown_detail_post)as TextView
        val attachfile=findViewById(R.id.breakdown_detail_attach_file) as TextView
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setTitle("Maintenance Details")
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener { finish() }
        /*var model = orderpartmodel(partname = "Camshaft", quantity = "1",needdate = "20-01-2014",description = "hhsd",status = "pending")
        var model1 = orderpartmodel(partname = "Camshaft", quantity = "1",needdate = "20-01-2014",description = "hhsd",status = "pending")

        orderpart.add(model)
        orderpart.add(model1)*/

        orderedAdapter=MaintenanceManagerOrederPartAdapter(this,orderpart)
        maintenanceorderpart_recyclerview.adapter=orderedAdapter
        maintenanceorderpart_recyclerview.layoutManager=LinearLayoutManager(this!!)
        maintenanceorderpart_recyclerview.isNestedScrollingEnabled=true
        customAdapter=MaintenanceManagerCustompartAdapter(this,custompart)
        maintenancecustompart_recyclerview.adapter=customAdapter
        maintenancecustompart_recyclerview.layoutManager=LinearLayoutManager(this!!)
        maintenancecustompart_recyclerview.isNestedScrollingEnabled=true



        // Handler code here.
         spinner=findViewById(R.id.breakdown_detail_mp)as Spinner
         var update:Button =findViewById(R.id.breakdown_detail_update)as Button
        ApiClient.getClient().create(NetworkApi::class.java)
            .getAllTechnician(Constant.getHeaderMap())
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(technicianlistObserver())

        val btrp: Button = findViewById(R.id.breakdown_detail_rp)as Button
        //sharedpreferences = SharedPreferenceUtils.getInstance(applicationContext)
        requestId = intent.getStringExtra("request_id")
        //sharedpreferences!!.setValue("request_id",requestId)
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
       spinner!!.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
           override fun onNothingSelected(parent: AdapterView<*>?) {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }

           override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
               if (position==0) {
                 technicianid= null

               } else {
                   val item :String=parent.getItemAtPosition(position).toString()
                   val opId = findViewById<View>(R.id.spinnerid) as TextView
                   opId.text = technicianId.get(position)
                   technicianid = opId.text.toString()
                   Log.e("Id", technicianid.toString())
               }
           }

           }



        btrp.setOnClickListener {
            val intent = Intent(this, MaintenanceManagerStoreRoomActivity::class.java)
            intent.putExtra("request_id",requestId)
            startActivity(intent)
        }
        update.setOnClickListener(View.OnClickListener {
            if (technicianid.isNullOrEmpty()){
                Toast.makeText(applicationContext,"Please select Technician",Toast.LENGTH_SHORT).show()

            }
           else{
                val assigntechnician=AssignTechnician(
                    technician_id = technicianid
                )
                myLoader = MyProgressDialog(this)
                myLoader.show()
                ApiClient.getClient().create(NetworkApi::class.java)
                    .AssignTechnician(Constant.getHeaderMap(),requestId!!.toInt(), assigntechnician)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(assignTechnicianObserver())
            }
        })
        myLoader = MyProgressDialog(this)
        myLoader.show()
        ApiClient.getClient().create(NetworkApi::class.java)
            .getBreakdownRequestDetails(Constant.getHeaderMap(), requestId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(breakdownRequestObserver())

        commentAdapter= MaintenanceMangerCommentAdapter(this, commentList)
        breakdown_detail_commentrecyclerview.adapter=commentAdapter
        breakdown_detail_commentrecyclerview.layoutManager = LinearLayoutManager(this)
        breakdown_detail_commentrecyclerview.isNestedScrollingEnabled = true
        breakdown_detail_galleryrecyclerview.adapter = BreakDownDetailOMGalleryAdapter(this, gallryList)
        breakdown_detail_galleryrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        breakdown_detail_galleryrecyclerview.isNestedScrollingEnabled = false

        standardAdapter = MaintenanceMangerPartNameAdapter(this, partName)
        breakdown_detail_part_namerecyclerview.adapter= standardAdapter
        breakdown_detail_part_namerecyclerview.layoutManager = LinearLayoutManager(applicationContext)

       /* breakdown_detail_galleryrecyclerview.adapter = BreakDownDetailOMGalleryAdapter(this, gallryList)
        breakdown_detail_galleryrecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        breakdown_detail_galleryrecyclerview.isNestedScrollingEnabled = false*/

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
                Toast.makeText(this@MaintenanceManagementBreakdownRequestDetailActivity,"Comment is successfully",Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()

                Toast.makeText(applicationContext, ""+e, Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun assignTechnicianObserver(): Observer<MainResponseModel> {
        return object : Observer<MainResponseModel> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MainResponseModel) {
                Log.d("Response", Gson().toJson(t))
               // startActivity(Intent(applicationContext, ManagementUserPreviewActivity::class.java))
                Toast.makeText(applicationContext,"Technician assign successfully",Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                Toast.makeText(applicationContext, ""+e, Toast.LENGTH_SHORT).show()
            }

        }
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
                val list = t.technicianRequests
                for(i in list){
                    val  technicianname:String=i.fullname
                    val technician:String=i.id.toString()
                    technicianId.add(technician)

                    technicianlist.add(technicianname)

                }
                technicianlist.add(0,"Select Technician")

                //adapter.addAllTechnicianeRequest(t.technicianRequests)
             val arrayAdapter = ArrayAdapter(this@MaintenanceManagementBreakdownRequestDetailActivity, android.R.layout.simple_spinner_item, technicianlist)
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner!!.adapter = arrayAdapter
            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
                //Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext,""+e,Toast.LENGTH_SHORT).show()



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

                breakdown_detail_requestId.text = String.format("Request ID: %s",breakdownRequestResponse.breakdownRequest!!.id)
                breakdown_detail_machineno.text = String.format("Machine NO: %s",breakdownRequestResponse.breakdownRequest!!.machineNumber)
                breakdown_detail_comment.text = breakdownRequestResponse.breakdownRequest!!.requestDescription
                breakdown_detail_commentdescription.text = breakdownRequestResponse.breakdownRequest!!.requestDescription
                breakdown_detail_creat_date.text =String.format("%s %s", breakdownRequestResponse.breakdownRequest!!.requestDate,breakdownRequestResponse.breakdownRequest!!.requestTime)
                breakdown_detail_suggestedpart.text =String.format("Suggested Part - %s", breakdownRequestResponse.breakdownRequest!!.suggestedPart)
                var orderresponse= breakdownRequestResponse.breakdownRequest!!.orders
                var standardorder=orderresponse!!.standardPart
                var orderpart=orderresponse!!.orderPart
                var custompart=orderresponse!!.customPart
                var comment=breakdownRequestResponse.breakdownRequest.comments
                standardAdapter.addAllStandardpart(standardorder)
                orderedAdapter.addAllOrderpart(orderpart)
                customAdapter.addAllCustompart(custompart)
                commentAdapter.addAllComment(comment)
            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
                Toast.makeText(this@MaintenanceManagementBreakdownRequestDetailActivity,""+e,Toast.LENGTH_SHORT).show()
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