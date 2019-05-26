package com.appgenesis.com.maintenanceapp.operation_manager.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.model.BreakdownRequest
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerBreakdownrequestDetailActivity
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerMaintenancerequestDetailActivity
import com.appgenesis.com.maintenanceapp.operation_manager.activity.OperationManagerPreventiverequestDetailActivity

class OperationMangerClosedRequestAdapter(
    val mContext: Context?,
    private val breakdownRequestList: ArrayList<BreakdownRequest>, val type: String
) : RecyclerView.Adapter<OperationMangerClosedRequestAdapter.MyViewHolder>() {


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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (type.equals("completed", true)) {
            holder.closed.visibility = View.GONE
            holder.completed.visibility = View.VISIBLE
            holder.cmpltdclslayout.background.setTint(ContextCompat.getColor(mContext!!, R.color.green))
        } else {
            holder.completed.visibility = View.GONE
            holder.closed.visibility = View.VISIBLE
            holder.cmpltdclslayout.background.setTint(ContextCompat.getColor(mContext!!, R.color.activeTextColor))
        }

        holder.comment.text = breakdownRequestList[holder.adapterPosition].requestDescription
        holder.requestId.text = String.format("Request ID: %d", breakdownRequestList[holder.adapterPosition].id)
        holder.machineNo.text = String.format("M/C No: %s", breakdownRequestList[holder.adapterPosition].machineNumber)
        holder.createdDate.text = String.format(
            "Created Date: %s, %s",
            breakdownRequestList[holder.adapterPosition].requestDate,
            breakdownRequestList[holder.adapterPosition].requestTime
        )
        holder.suggestedPart.text =
            String.format("Suggested Part: %s", breakdownRequestList[holder.adapterPosition].suggestedPart)

        if (type.equals("breakdown")) {
            holder.itemView.setOnClickListener {

                val intent = Intent(mContext, OperationManagerBreakdownrequestDetailActivity::class.java)
                intent.putExtra("request_id", (breakdownRequestList[holder.adapterPosition].id).toString())
                mContext.startActivity(intent)
            }
        }
        if (type.equals("mr")) {
            holder.itemView.setOnClickListener {

                val intent = Intent(mContext, OperationManagerMaintenancerequestDetailActivity::class.java)
                intent.putExtra("request_id", (breakdownRequestList[holder.adapterPosition].id).toString())
                mContext.startActivity(intent)
            }
        }
        if (type.equals("preventive")) {
            holder.itemView.setOnClickListener {

                val intent = Intent(mContext, OperationManagerPreventiverequestDetailActivity::class.java)
                intent.putExtra("request_id", (breakdownRequestList[holder.adapterPosition].id).toString())
                mContext.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return breakdownRequestList.size
    }

    fun addAllBreakdownRequest(breakdownRequests: List<BreakdownRequest>) {
        breakdownRequestList.clear()
        notifyDataSetChanged()
        breakdownRequestList.addAll(breakdownRequests)
        notifyDataSetChanged()
    }

    interface OnItemClick {
        fun onSelect(position: Int)
    }

}