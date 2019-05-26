package com.appgenesis.com.maintenanceapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.MyApplication
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.activity.MaintenanceManagerDashbordActivity
import com.appgenesis.com.maintenanceapp.management.activity.ManagementDashboardActivity
import com.appgenesis.com.maintenanceapp.model.LoginResponseModel
import com.appgenesis.com.maintenanceapp.model.UserRequest
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.ConnectionDetector
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerDashboardActivity
import com.appgenesis.com.maintenanceapp.store.activity.StoreDashboardActivity
import com.appgenesis.com.maintenanceapp.technician.activity.TechnicianDashboardActivity
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.appgenesis.com.maintenanceapp.utils.TOKEN
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_login.*
import com.appgenesis.com.maintenanceapp.utils.SharedPreferenceUtils

class LoginFragment : Fragment() {
    private lateinit var myLoader: MyProgressDialog
    lateinit var cd: ConnectionDetector
    var sharedpreferences: SharedPreferenceUtils? = null
    var id: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cd = ConnectionDetector()
        cd.isConnectingToInternet(this!!.activity!!)
        sharedpreferences = SharedPreferenceUtils.getInstance(this!!.activity!!)
        forgotpassword.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "closed")
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.loginmainlaypout, ForgotPasswordFragment.newInstance(bundle),
                    ForgotPasswordFragment::class.java.name
                )
                .commit()
        }

        login.setOnClickListener {
            if (cd.isConnectingToInternet(this!!.activity!!)) {
                when {
                    username.text.isEmpty() -> Toast.makeText(
                        context,
                        "Please enter your username",
                        Toast.LENGTH_SHORT
                    ).show()
                    password.text.isEmpty() -> Toast.makeText(
                        context,
                        "Please enter your password",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        myLoader = MyProgressDialog(this.context!!)
                        myLoader.show()
                        val userRequest =
                            UserRequest(user_email = username.text.toString(), password = password.text.toString())
                        ApiClient.getClient().create(NetworkApi::class.java)
                            .loginUser(Constant.getHeaderMap(), userRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(loginObserver())

                    }
                }
            }
            else{
                Toast.makeText(activity,
                    "No Internet Connection", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun loginObserver(): Observer<LoginResponseModel> {
        return object : Observer<LoginResponseModel> {
            override fun onComplete() {
                if(remember_check.isChecked)
                  sharedpreferences!!.setValue("Registered",true);

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: LoginResponseModel) {
                //Toast.makeText(context, ""+t, Toast.LENGTH_SHORT).show()
                myLoader.dismiss()
                Log.d("Response", Gson().toJson(t))
                val useremail=t.user.userEmail
                val userrole=t.user.userRole
                sharedpreferences!!.setValue("UserRole",userrole)
                val id=t.user.id
              /*  val editor = sharedpreferences!!.edit()*/
              /*  editor.putBoolean(session_status, true)*/
              /*  editor.putString(TAG_ID, id.toString())*/
              /*  editor.putString(TAG_USEREMAIL, useremail)*/
              /*  editor.commit()  */
              /*  if (userrole.equals("Admin")) {*/
              /*      val intent = Intent(activity, ManagementDashboardActivity::class.java)*/
              /*      intent.putExtra(TAG_ID, id)*/
              /*      intent.putExtra(TAG_USEREMAIL,useremail)*/
              /*      startActivity(intent)*/
              /*  }*/
/*
*/

                when(userrole){
                    "Admin"->startActivity(Intent(activity, ManagementDashboardActivity::class.java))
                    "OperationManager"->startActivity(Intent(activity,OperationManagerDashboardActivity::class.java))
                    "MaintenanceManager"->startActivity(Intent(activity, MaintenanceManagerDashbordActivity::class.java))
                    "Store"->startActivity(Intent(activity, StoreDashboardActivity::class.java))
                    "Technician"->startActivity(Intent(activity, TechnicianDashboardActivity::class.java))

                }

                MyApplication.sharedPreferenceUtils.setValue("PROFILE_VAL",Gson().toJson(t))
                MyApplication.sharedPreferenceUtils.setValue(TOKEN,t.accessToken)

            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
            }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        fun newInstance(args: Bundle): LoginFragment {
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
