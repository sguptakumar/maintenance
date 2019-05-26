package com.appgenesis.com.maintenanceapp.operation_manager.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.model.CreateBreakdownRequest
import com.appgenesis.com.maintenanceapp.model.MainResponseModel
import com.appgenesis.com.maintenanceapp.model.UploadObject
import com.appgenesis.com.maintenanceapp.model.Uploadimage
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_break_down_request.*
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_date
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_description
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_machine_no
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_priority
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_submit
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_suggestedpart
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_time
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_title
import kotlinx.android.synthetic.main.activity_add_break_down_request.activity_add_break_uploadimage
import kotlinx.android.synthetic.main.activity_management_create_preventiv_maintenanc.*
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AddBreakDownRequestActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100
    private lateinit var encodedImage: String
    private lateinit var encoded: String
    val concat:String="data:image/png;base64,"
    var type: String = ""
    private lateinit var myLoader: MyProgressDialog
    val myCalendar = Calendar.getInstance()
    var image:String?=null
    var priority:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.appgenesis.com.maintenanceapp.R.layout.activity_add_break_down_request)
        val bundle = intent.extras
        if (bundle != null) {
            type = bundle.getString("type", "")
        }

        when {
            type.equals("mr", true) -> {
                activity_add_break_priority.visibility = View.VISIBLE
                supportActionBar!!.title = "Create Maintenance Request"

            }
            type.equals("preventive", true) -> {
                activity_add_break_priority.visibility = View.VISIBLE
                supportActionBar!!.title = "Create Preventive Request"
            }
            type.equals("breakdown", true) -> {
                activity_add_break_priority.visibility = View.VISIBLE
                supportActionBar!!.title = "Create Breakdown Request"
            }
        }
        activity_add_break_priority.setOnClickListener(View.OnClickListener {
            val listitems= arrayOf("low","medium","high")
            val mBuilder= AlertDialog.Builder(this)
            mBuilder.setTitle("Select Priority")
            mBuilder.setSingleChoiceItems(listitems,-1){
                    dialog: DialogInterface?, i: Int ->
                priority=listitems[i]
                activity_add_break_priority.setText(priority)

                dialog!!.dismiss()

            }
            mBuilder.setNeutralButton("cancel"){ dialog: DialogInterface?, which: Int ->
                dialog!!.cancel()
            }
            val mDialog=mBuilder.create()
            mDialog.show()

        })

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        activity_add_break_date.setOnClickListener {
            DatePickerDialog(
                this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        activity_add_break_time.setOnClickListener {
            val hour = myCalendar.get(Calendar.HOUR_OF_DAY)
            val minute = myCalendar.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    activity_add_break_time.setText(
                        "$selectedHour:$selectedMinute"
                    )
                }, hour, minute, true
            )//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        activity_add_break_uploadimage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_PERMISSION_CODE
                    )
                } else {
                    val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST)
                }
            }
        }
        activity_add_break_submit.setOnClickListener {

            when {
                activity_add_break_machine_no.text.isEmpty() -> Toast.makeText(
                    this,
                    "Please enter your machine number",
                    Toast.LENGTH_SHORT
                ).show()
                activity_add_break_title.text.isEmpty() -> Toast.makeText(
                    this, "Please enter your title",
                    Toast.LENGTH_SHORT
                ).show()
                activity_add_break_date.text.isEmpty() -> Toast.makeText(
                    this, "Please enter your date",
                    Toast.LENGTH_SHORT
                ).show()
                activity_add_break_time.text.isEmpty() -> Toast.makeText(
                    this, "Please enter your time",
                    Toast.LENGTH_SHORT
                ).show()
                activity_add_break_priority.text.isEmpty() -> Toast.makeText(
                    this, "Please Select Priority",
                    Toast.LENGTH_SHORT
                ).show()
                activity_add_break_suggestedpart.text.isEmpty() -> Toast.makeText(
                    this, "Please enter your suggested part",
                    Toast.LENGTH_SHORT
                ).show()
                activity_add_break_description.text.isEmpty() -> Toast.makeText(
                    this, "Please enter your description",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    val breakdownRequest = CreateBreakdownRequest(
                        machine_number = activity_add_break_machine_no.text.toString(),
                        title = activity_add_break_title.text.toString(),
                        request_date = activity_add_break_date.text.toString(),
                        request_time = activity_add_break_time.text.toString(),
                        priority = priority,
                        suggested_part = activity_add_break_suggestedpart.text.toString(),
                        request_description = activity_add_break_description.text.toString(),
                        request_image = image
                        //request_image = "dadjasdagdyaskdhkashdashduk"
                    )

                    myLoader = MyProgressDialog(this!!)
                    myLoader.show()

                    if (type.equals("breakdown", true)) {
                        ApiClient.getClient().create(NetworkApi::class.java)
                            .createBreakdownRequest(Constant.getHeaderMap(), breakdownRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(createBreakdownObserver())
                    }

                    if (type.equals("mr")) {

                        ApiClient.getClient().create(NetworkApi::class.java)
                            .createMaintenanceRequest(Constant.getHeaderMap(), breakdownRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(createBreakdownObserver())

                    }
                }
            }

        }

    }

    private fun createBreakdownObserver(): Observer<MainResponseModel> {
        return object : Observer<MainResponseModel> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MainResponseModel) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                Toast.makeText(this@AddBreakDownRequestActivity,"Request Created Successfully",Toast.LENGTH_SHORT).show()
                if (type.equals("breakdown", true)) {
                    val intent = Intent(this@AddBreakDownRequestActivity, BreakDownActivity::class.java)
                    intent.putExtra("type", "breakdown")

                    startActivity(intent)

                }
                if (type.equals("mr")) {
                    val intent =
                        Intent(this@AddBreakDownRequestActivity, MaintenanceRequestActivityOperationManager::class.java)
                    intent.putExtra("type", "mr")
                    startActivity(intent)


                }


            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()

                Toast.makeText(applicationContext, "" + e, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun updateLabel() {
        val myFormat = "YYYY-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        activity_add_break_date.setText(sdf.format(myCalendar.time))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        } else
            Toast.makeText(
                this,
                "Please provide permission..", Toast.LENGTH_SHORT
            ).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()

            encoded = android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
            encodedImage=concat+encoded
                val uploadimage=UploadObject(
                    requestImage = encodedImage
                )
            myLoader = MyProgressDialog(this!!)
            myLoader.show()
                ApiClient.getClient().create(NetworkApi::class.java)
                    .uploadFile(Constant.getHeaderMap(),uploadimage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(imageObserver())




        }

    }
    private fun imageObserver(): Observer<Uploadimage> {
        return object : Observer<Uploadimage> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Uploadimage) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                 image=t.path.request_image
                Toast.makeText(applicationContext, "image uploaded Successfully", Toast.LENGTH_SHORT).show()
                //Toast.makeText(applicationContext,""+image,Toast.LENGTH_SHORT).show()



            }

            override fun onError(e: Throwable) {
                //myLoader.dismiss()

                Toast.makeText(applicationContext, "" + e, Toast.LENGTH_SHORT).show()
            }

        }
    }
}









