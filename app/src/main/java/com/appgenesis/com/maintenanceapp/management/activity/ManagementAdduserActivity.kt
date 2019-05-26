package com.appgenesis.com.maintenanceapp.management.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.model.*
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.activity.BreakDownActivity
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerPreventiveRequestActivity
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ManagementAdduserActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100
    private lateinit var encodedImage: String
    private lateinit var encoded: String
    val concat:String="data:image/png;base64,"
    var image:String?=null
    internal var EMAIL_STRING =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    internal var p: Pattern?=null
    internal var m: Matcher?=null
    private var check: Boolean = false

    internal var firstname = ""
    internal var lastname=""
    internal var email = ""
    internal var mobile = ""
    internal var password=""
    internal var uploadImage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_adduser)
        val username = findViewById(R.id.activity_add_user_firstname) as EditText
        val userlastname = findViewById(R.id.activity_add_user_lastname) as EditText
        val useremail = findViewById(R.id.activity_add_user_email) as EditText
        val usercall = findViewById(R.id.activity_add_user_call) as EditText
        val userpassword = findViewById(R.id.activity_add_user_password) as EditText
        val userimage = findViewById(R.id.activity_management_upload_image) as TextView
        val usersubmit = findViewById(R.id.activity_add_user_submit) as Button
        p = Pattern.compile(EMAIL_STRING)
        usersubmit.setOnClickListener(View.OnClickListener {
            firstname = username.getText().toString()
            lastname = userlastname.getText().toString()
            email = useremail.getText().toString()
            mobile = usercall.getText().toString()
            password = userpassword.getText().toString()
            m = p!!.matcher(email)
            check = m!!.matches()
            if (firstname.isEmpty()) {
                username.setError("Please Enter First Name")
                username.requestFocus()
            } else if (lastname.isEmpty()) {
                userlastname.setError("Please Enter Last Name")
                userlastname.requestFocus()
            } else if (email.isEmpty()) {
                useremail.setError("Please Enter Correect Email Address")
                useremail.requestFocus()
            } else if (!check) {
                useremail.setError("Please Enter Correect Email Address")
                useremail.requestFocus()
            } else if (mobile.length < 10 || mobile.length > 10) {
                usercall.setError("Please Enter Correct Mobile Number")
                usercall.requestFocus()
            } else if (password.length < 6) {
                userpassword.setError("Please Enter 6 digit password")
                userpassword.requestFocus()
            } else {
                val createTechnician = CreateTechnician(
                    user_first_name = firstname,
                    user_last_name = lastname,
                    user_email = email,
                    password = password,
                    user_phone = mobile,
                    user_image = image
                )
                ApiClient.getClient().create(NetworkApi::class.java)
                    .createTechnicianRequest(Constant.getHeaderMap(), createTechnician)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(createTechnicianObserver())
            }


        })



        userimage.setOnClickListener {
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
            val uploadimage= UploadObject(
                requestImage = encodedImage
            )

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
                //myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Uploadimage) {
                //myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                Toast.makeText(applicationContext, "image uploaded"+t, Toast.LENGTH_SHORT).show()
                image=t.path.request_image
                //Toast.makeText(applicationContext,""+image,Toast.LENGTH_SHORT).show()



            }

            override fun onError(e: Throwable) {
                //myLoader.dismiss()

                Toast.makeText(applicationContext, "" + e, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun createTechnicianObserver(): Observer<MainResponseModel> {
        return object : Observer<MainResponseModel> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MainResponseModel) {
                Log.d("Response", Gson().toJson(t))
                startActivity(Intent(applicationContext,ManagementUserPreviewActivity::class.java))
            }

            override fun onError(e: Throwable) {
                Toast.makeText(applicationContext, ""+e, Toast.LENGTH_SHORT).show()
            }

        }
    }






}
