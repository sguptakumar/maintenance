package com.appgenesis.com.maintenanceapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R


class RegistrationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    companion object {
        fun newInstance(args: Bundle): RegistrationFragment {
            val fragment = RegistrationFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
