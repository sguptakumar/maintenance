package com.appgenesis.com.maintenanceapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MyProgressDialog(context: Context) {

    internal var dialog: Dialog? = null
    private val progressBar: ProgressBar

    val isShowing: Boolean
        get() = dialog!!.isShowing

    init {

        dialog = Dialog(context)
        dialog!!.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)


        val relativeLayout = RelativeLayout(context)
        val layoutParams =
            RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        relativeLayout.layoutParams = layoutParams
        progressBar = ProgressBar(context)
        val layoutParams_progress =
            RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        layoutParams_progress.addRule(RelativeLayout.CENTER_IN_PARENT)

        val linearlayoutParams_progress =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        linearlayoutParams_progress.gravity = Gravity.CENTER
        progressBar.layoutParams = layoutParams_progress

        relativeLayout.addView(progressBar)

        dialog!!.getWindow().setContentView(relativeLayout, layoutParams)
        dialog!!.getWindow().setBackgroundDrawable(
            ColorDrawable(android.graphics.Color.TRANSPARENT)
        )


    }

    fun show() {

        if (!dialog!!.isShowing && dialog != null) {
            dialog!!.show()

        }

    }

    fun dismiss() {

        if (dialog!!.isShowing && dialog != null) {
            dialog!!.dismiss()
        }
    }

    fun setCancelable(cancelable: Boolean) {
        dialog!!.setCancelable(cancelable)
    }


    fun setCanceledOnTouchOutside(flag: Boolean) {
        dialog!!.setCanceledOnTouchOutside(flag)
    }

    fun setColor(colour: Int) {
        progressBar.indeterminateDrawable.setColorFilter(colour, android.graphics.PorterDuff.Mode.MULTIPLY)
    }


}