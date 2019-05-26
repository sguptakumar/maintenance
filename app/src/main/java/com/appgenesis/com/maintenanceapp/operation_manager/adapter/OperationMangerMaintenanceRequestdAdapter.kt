package com.appgenesis.com.maintenanceapp.operation_manager.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.model.MaintenanceRequest
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerBreakdownrequestDetailActivity

class OperationMangerMaintenanceRequestdAdapter(
    val mContext: Context?, val maintenancerequestList: ArrayList<MaintenanceRequest>,val type:String)
    : RecyclerView.Adapter<OperationMangerMaintenanceRequestdAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Requestedid = view.findViewById<View>(R.id.requestId)as TextView
        val High=view.findViewById<View>(R.id.high)as TextView
        val Closeed=view.findViewById<View>(R.id.close)as TextView
        val Machineno=view.findViewById<View>(R.id.machineno)as TextView
        val machicomment=view.findViewById<View>(R.id.comnt)as TextView
        val Createddate=view.findViewById<View>(R.id.creat_date)as TextView
        val Completedate=view.findViewById<View>(R.id.complete_date)as TextView
        val Namet=view.findViewById<View>(R.id.name)as TextView
        val Suggestedpart=view.findViewById<View>(R.id.suggestedpart)as TextView




    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.break_down_request_item_layout, parent, false)
        return MyViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.machicomment.text = maintenancerequestList[holder.adapterPosition].requestDescription
        holder.Requestedid.text = String.format("Request ID: %d", maintenancerequestList[holder.adapterPosition].id)
        holder.Machineno.text = String.format("M/C No: %s", maintenancerequestList[holder.adapterPosition].machineNumber)
        holder.Createddate.text = String.format(
            "Created Date: %s, %s",
            maintenancerequestList[holder.adapterPosition].requestDate,
            maintenancerequestList[holder.adapterPosition].requestTime
        )
        holder.Suggestedpart.text =
            String.format("Suggested Part: %s", maintenancerequestList[holder.adapterPosition].suggestedPart)


        holder.itemView.setOnClickListener {
            val intent = Intent(mContext,OperationManagerBreakdownrequestDetailActivity::class.java)
            mContext!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return maintenancerequestList.size
    }
    fun addAllMaintenanceRequest(maintenanceRequest: List<MaintenanceRequest>) {
        maintenancerequestList.addAll(maintenanceRequest)
        notifyDataSetChanged()
    }

}