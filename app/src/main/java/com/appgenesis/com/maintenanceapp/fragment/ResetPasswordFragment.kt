package com.appgenesis.com.maintenanceapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.LoginRegistrationActivity

import com.appgenesis.com.maintenanceapp.R
import kotlinx.android.synthetic.main.fragment_reset_password.*


class ResetPasswordFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitpassword.setOnClickListener {
            val intent = Intent(activity,LoginRegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }


    companion object {
        fun newInstance(args: Bundle): ResetPasswordFragment {
            val fragment = ResetPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
