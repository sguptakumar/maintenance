package com.appgenesis.com.maintenanceapp.management.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.management.activity.ManagementPreventiveRequestDetailActivity
import com.appgenesis.com.maintenanceapp.model.BreakdownRequest

class ManagementClosedRequestAdapter(val mContext: Context?,
                                     val licenceList: ArrayList<BreakdownRequest>, val type:String)
    : RecyclerView.Adapter<ManagementClosedRequestAdapter.MyViewHolder>() {



    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cmpltdclslayout = view.findViewById<View>(R.id.closed_req_cmpltdclslayout)
        val closed = view.findViewById<View>(R.id.closed_req_closed)
        val completed = view.findViewById<View>(R.id.closed_req_completed)


        val machineNo = view.findViewById<View>(R.id.closed_req_machineno) as TextView
        val comment = view.findViewById<View>(R.id.closed_req_comnt) as TextView
        val name = view.findViewById<View>(R.id.closed_req_name) as TextView
        val completeDate = view.findViewById<View>(R.id.closed_req_complete_date) as TextView
        val createdDate = view.findViewById<View>(R.id.closed_req_create_date) as TextView
        val suggestedPart = view.findViewById<View>(R.id.closed_req_suggestedpart) as TextView
        val requestId = view.findViewById<View>(R.id.closed_req_id) as TextView


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.closed_request_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

       /* if (type.equals("completed")){
            holder.closed.visibility = View.GONE
            holder.completed.visibility = View.VISIBLE
            holder.cmpltdclslayout.background.setTint(ContextCompat.getColor(mContext!!,R.color.green))
        }
        else {
            holder.completed.visibility = View.GONE
            holder.closed.visibility = View.VISIBLE
            holder.cmpltdclslayout.background.setTint(ContextCompat.getColor(mContext!!,R.color.activeTextColor))


        }*/
        holder.comment.text = licenceList[holder.adapterPosition].requestDescription
        holder.requestId.text = String.format("Request ID: %d", licenceList[holder.adapterPosition].id)
        holder.machineNo.text = String.format("M/C No: %s", licenceList[holder.adapterPosition].machineNumber)
        holder.createdDate.text = String.format(
            "Created Date: %s, %s",
            licenceList[holder.adapterPosition].requestDate,
            licenceList[holder.adapterPosition].requestTime
        )
        holder.suggestedPart.text =
            String.format("Suggested Part: %s", licenceList[holder.adapterPosition].suggestedPart)


        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, ManagementPreventiveRequestDetailActivity::class.java)
            intent.putExtra("request_id",(licenceList[holder.adapterPosition].id).toString())
            mContext!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return licenceList.size
    }
    interface OnItemClick {
        fun onSelect(position: Int)
    }
    fun addAllBreakdownRequest(breakdownRequests: List<BreakdownRequest>) {
       licenceList.clear()
        notifyDataSetChanged()
        licenceList.addAll(breakdownRequests)
        notifyDataSetChanged()
    }

}