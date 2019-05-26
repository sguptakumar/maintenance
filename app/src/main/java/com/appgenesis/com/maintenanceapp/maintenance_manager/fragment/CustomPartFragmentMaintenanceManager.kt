package com.appgenesis.com.maintenanceapp.maintenance_manager.fragment

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.CustomOrder
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.ServerResponse
import com.appgenesis.com.maintenanceapp.model.MainResponseModel
import com.appgenesis.com.maintenanceapp.model.UploadObject
import com.appgenesis.com.maintenanceapp.model.Uploadimage
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakDownList
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_custom_part_fragment_maintenance_manager.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*



class CustomPartFragmentMaintenanceManager : Fragment() {
    internal var pdfUri: Uri? = null
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100
    private lateinit var encodedImage: String
    private lateinit var encoded: String
    val concat:String="data:image/png;base64,"
    val operationManger = ArrayList<BreakDownList>()
    val myCalendar = Calendar.getInstance()
    private lateinit var myLoader: MyProgressDialog
    var path:String?=null
    var image:String?=null
    private var mpdfUrl = ""
    var requestedid:String?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_custom_part_fragment_maintenance_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        requestedid = args!!.getString("request_id")
        customuploadfile.setOnClickListener(View.OnClickListener {
            if(ContextCompat.checkSelfPermission(this!!.activity!!, Manifest.permission.READ_EXTERNAL_STORAGE)
                ==PackageManager.PERMISSION_GRANTED){
                selectPdf()

            }
            else{
                ActivityCompat.requestPermissions(
                    this!!.activity!!,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    9
                )
            }
        })
        customuploadimage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this!!.activity!!,Manifest.permission.CAMERA)
                    !=PackageManager.PERMISSION_GRANTED){
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        MY_CAMERA_PERMISSION_CODE
                    )
                }

             else {
                    val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST)
                }
            }
        }

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }
        customselectdate.setOnClickListener(View.OnClickListener {
            DatePickerDialog(activity,date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        })
        submit.setOnClickListener(View.OnClickListener {
            when{
                custompartname.text.isEmpty()->Toast.makeText(activity,"Please Enter Part Name",
                    Toast.LENGTH_SHORT).show()

                customdescription.text.isEmpty()->Toast.makeText(activity,"Please Enter Description",
                    Toast.LENGTH_SHORT).show()

                customcomment.text.isEmpty()->
                    Toast.makeText(activity,"Please Enter Comment",Toast.LENGTH_SHORT).show()
                customquantity.text.isEmpty()->
                    Toast.makeText(activity,"Please Enter Quantity",Toast.LENGTH_SHORT).show()
                customselectdate.text.isEmpty()->
                    Toast.makeText(activity,"Please Select Date",Toast.LENGTH_SHORT).show()
                customuploadfile.text.isEmpty()->
                    Toast.makeText(activity,"Please Select Pdf",Toast.LENGTH_SHORT).show()
                /*customuploadimage.text.isEmpty()->
                    Toast.makeText(activity,"Please Select Image",Toast.LENGTH_SHORT).show()*/
                 else ->{
                     val customorder= CustomOrder(
                         request_id = requestedid,
                         part_name = custompartname.text.toString(),
                         quantity = customquantity.text.toString(),
                         description = customdescription.text.toString(),
                         need_by_date = customselectdate.text.toString(),
                         file =path,
                         image = image
                     )
                     myLoader = MyProgressDialog(activity!!)
                     myLoader.show()

                     ApiClient.getClient().create(NetworkApi::class.java)
                         .createCustomOrder(Constant.getHeaderMap(),customorder)
                         .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                         .subscribe(createCustomorderObserver())
                 }
            }

        })


    }
    private fun createCustomorderObserver(): Observer<MainResponseModel> {
        return object : Observer<MainResponseModel> {
            override fun onComplete() {
                myLoader.dismiss()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MainResponseModel) {
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                Toast.makeText(activity,"Order is Successful",Toast.LENGTH_SHORT).show()



            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()

                Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun updateLabel() {
        val myFormat = "YYYY-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        customselectdate.setText(sdf.format(myCalendar.time))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf()
        }
        else if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(activity, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else
            Toast.makeText(activity,
                "Please provide permission..", Toast.LENGTH_SHORT).show()
    }
    private fun selectPdf() {
        val intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 86)
    }
    private fun uploadResumeToServer(pathToVideoFile: String) {

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 86 && resultCode == Activity.RESULT_OK && data != null) {
            pdfUri = data.data
            path=pdfUri!!.path


            // val filePath = getRealPathFromURI(pdfUri!!)

           /* val videoFile = File(path)*/
//RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

            val videoBody = RequestBody.create(MediaType.parse("*/*"), path)
            val vFile = MultipartBody.Part.createFormData("file", path, videoBody)
            val filename:RequestBody = RequestBody.create(MediaType.parse("text/plain"), path);
            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val vInterface = retrofit.create(NetworkApi::class.java)
            val serverCom = vInterface.uploadmultipartfile(Constant.getHeaderMap(),vFile,filename)
            serverCom.enqueue(object : Callback<ServerResponse> {
                override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Toast.makeText(activity,""+response,Toast.LENGTH_SHORT).show()
                        Toast.makeText(activity,""+result,Toast.LENGTH_SHORT).show()

                    }
                    else{

                    }
                }

                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {

                }
            })
            //path=pdfUri!!.path
            /* val filePath = getRealPathFromURI(pdfUri!!)
           val file = File(filePath)
           // Log.d(TAG, "Filename " + file.name)
            //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            val mFile = RequestBody.create(MediaType.parse("application/pdf"), file)
            val fileToUpload = MultipartBody.Part.createFormData("requestImage", file.name, mFile)
            //val filename = RequestBody.create(MediaType.parse("request_image"), file.name)
            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val uploadImage = retrofit.create<NetworkApi>(NetworkApi::class.java)
            val fileUpload = uploadImage.uploadmultipartfile(Constant.getHeaderMap(),fileToUpload)
            fileUpload.enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                    Toast.makeText(activity, "Response " + response.raw().message(), Toast.LENGTH_LONG).show()
                    Toast.makeText(activity, "Success " + response, Toast.LENGTH_LONG).show();
                    val responseBody = response.body()
                    mpdfUrl = responseBody!!.path.toString()
                    Toast.makeText(activity,""+mpdfUrl,Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                  //  Log.d(TAG, "Error " + t.message)
                    Toast.makeText(activity,""+t,Toast.LENGTH_SHORT).show()
                    Toast.makeText(activity,""+call,Toast.LENGTH_SHORT).show()
                }
            })
        }*/
        }

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
            myLoader = MyProgressDialog(activity!!)
            myLoader.show()


            ApiClient.getClient().create(NetworkApi::class.java)
                .uploadFile(Constant.getHeaderMap(),uploadimage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageObserver())


        }
         /*   else {
            Toast.makeText(activity, "Please Select a file", Toast.LENGTH_SHORT).show()
        }*/

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
                 Toast.makeText(activity, "image uploaded"+t, Toast.LENGTH_SHORT).show()
                image=t.path.request_image
                //Toast.makeText(applicationContext,""+image,Toast.LENGTH_SHORT).show()



            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()

                Toast.makeText(activity, "" + e, Toast.LENGTH_SHORT).show()
            }

        }
    }



    companion object {
        fun newInstance(args: Bundle): CustomPartFragmentMaintenanceManager {
            val fragment = CustomPartFragmentMaintenanceManager()
            fragment.arguments = args
            return fragment
        }
    }
    private fun getRealPathFromURIPath(contentURI: Uri?, activity: Activity): String? {
        val cursor = activity.contentResolver.query(contentURI!!, null, null, null, null)
        if (cursor == null) {
            return contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }
    private fun getRealPathFromURI(contentUri:Uri):String {
        val proj = arrayOf<String>(MediaStore.Images.Media.DATA)
        val loader = activity?.let { CursorLoader(it, contentUri, proj, null, null, null) }
        val cursor = loader!!.loadInBackground()
        var column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        var result = cursor.getString(column_index)
        cursor.close()
        return result
    }

}
