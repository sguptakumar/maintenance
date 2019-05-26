package com.appgenesis.com.maintenanceapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.LoginRegistrationActivity
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.model.ForgotPasswordRequest
import com.appgenesis.com.maintenanceapp.model.MainResponseModel
import com.appgenesis.com.maintenanceapp.network.ApiClient
import com.appgenesis.com.maintenanceapp.network.Constant
import com.appgenesis.com.maintenanceapp.network.NetworkApi
import com.appgenesis.com.maintenanceapp.utils.MyProgressDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_forgot_password.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance(args: Bundle): ForgotPasswordFragment {
            val fragment = ForgotPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }


    private lateinit var myLoader: MyProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "closed")
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.loginmainlaypout, OtpFragment.newInstance(bundle),
                    OtpFragment::class.java.name
                )
                .commit()
        }

        submit.setOnClickListener {

            when {
                forgot_pass_username.text.isEmpty() -> Toast.makeText(
                    context,
                    "Please enter your username",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {

                    val forgotPasswordRequest = ForgotPasswordRequest(email = forgot_pass_username.text.toString())
                    myLoader = MyProgressDialog(this.context!!)
                    myLoader.show()
                    ApiClient.getClient().create(NetworkApi::class.java)
                        .forgotPassword(Constant.getHeaderMap(), forgotPasswordRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(forgotPasswordObserver())
                }
            }
        }
        activity!!.setTitle("Forgot Password")

    }

    private fun forgotPasswordObserver(): Observer<MainResponseModel> {
        return object : Observer<MainResponseModel> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MainResponseModel) {
                Log.d("Response", Gson().toJson(t))
                myLoader.dismiss()
                val startActivity = Intent(activity, LoginRegistrationActivity::class.java)
                startActivity(startActivity)
            }

            override fun onError(e: Throwable) {
                myLoader.dismiss()
            }

        }
    }
}
