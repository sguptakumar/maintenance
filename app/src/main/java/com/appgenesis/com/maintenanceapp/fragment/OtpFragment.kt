package com.appgenesis.com.maintenanceapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appgenesis.com.maintenanceapp.R
import kotlinx.android.synthetic.main.fragment_otp.*


class OtpFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitOtp.setOnClickListener {
            val  bundle = Bundle()
            bundle.putString("type","closed")
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.loginmainlaypout, ResetPasswordFragment.newInstance(bundle),
                    ResetPasswordFragment::class.java.name)
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    companion object {
        fun newInstance(args: Bundle): OtpFragment {
            val fragment = OtpFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
