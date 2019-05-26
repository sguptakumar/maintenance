package com.appgenesis.com.maintenanceapp.maintenance_manager.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.AlertDialog
import kotlinx.android.synthetic.main.activity_add_break_down_request.*
import java.text.SimpleDateFormat
import java.util.*

class AlertDialogAdapter(val mContext: Context, val licenceList: ArrayList<AlertDialog>)
    : RecyclerView.Adapter<AlertDialogAdapter.MyViewHolder>() {
    val myCalendar = Calendar.getInstance()
   // val dialogNeedbydate: EditText? = null


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dialogPartname: EditText = view.findViewById<View>(R.id.dialogPartname) as EditText
        val dialogQuantity: EditText = view.findViewById<View>(R.id.dialogQuantity) as EditText
        val dialogDiscription: EditText = view.findViewById<View>(R.id.dialogDiscription) as EditText
        val dialogNeedbydate: EditText = view.findViewById<View>(R.id.dialogNeedbydate) as EditText
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.maintenance_alert_dialog_adapter, parent, false)
        return MyViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "YYYY-MM-dd" //In which you need put here
            val sdf = SimpleDateFormat(myFormat, Locale.US)

            holder.dialogNeedbydate.setText(sdf.format(myCalendar.time))
        }
      holder.dialogNeedbydate.setOnClickListener(View.OnClickListener {
          DatePickerDialog(
              this.mContext!!, date, myCalendar
                  .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
              myCalendar.get(Calendar.DAY_OF_MONTH)
          ).show()
      })

        }




    override fun getItemCount(): Int {
        return licenceList.size
    }
}