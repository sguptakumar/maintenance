package com.appgenesis.com.maintenanceapp.store.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.store.activity.StorePartDetailActivity
import com.appgenesis.com.maintenanceapp.store.model.StoreRequest
import com.appgenesis.com.maintenanceapp.store.model.StoreReuestListResponse

class StoreClosedRequestAdapter(val mContext: Context?,
                                val storerequestList: ArrayList<StoreRequest>, val type:String)
    : RecyclerView.Adapter<StoreClosedRequestAdapter.MyViewHolder>() {



    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val closed_requested_id=view.findViewById<View>(R.id.closed_req_id)as TextView
            val closed_requested_close=view.findViewById<View>(R.id.closed_req_close)as TextView
            val closed_requested_closed=view.findViewById<View>(R.id.closed_req_closed)as TextView
            val closed_requested_machineno=view.findViewById<View>(R.id.closed_req_machineno)as TextView
            val closed_requested_comment=view.findViewById<View>(R.id.closed_req_comnt)as TextView
            val closed_requested_createdate=view.findViewById<View>(R.id.closed_req_create_date)as TextView
            val closed_requested_completedate=view.findViewById<View>(R.id.closed_req_complete_date)as TextView
            val closed_requesed_name=view.findViewById<View>(R.id.closed_req_name)as TextView
            val closed_requested_suggestedpart=view.findViewById<View>(R.id.closed_req_suggestedpart)as TextView


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.closed_request_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

      /*  if (type.equals("completed")){
            holder.closed.visibility = View.GONE
            holder.completed.visibility = View.VISIBLE
            holder.cmpltdclslayout.background.setTint(ContextCompat.getColor(mContext!!,R.color.green))
        }
        else {
            holder.completed.visibility = View.GONE
            holder.closed.visibility = View.VISIBLE
            holder.cmpltdclslayout.background.setTint(ContextCompat.getColor(mContext!!,R.color.activeTextColor))


        }*/
       // holder.requestId.text = String.format("Request ID: %d", breakdownRequestList[holder.adapterPosition].id)
        holder.closed_requested_id.text= String.format("Request ID %d",storerequestList[holder.adapterPosition].id)
        holder.closed_requested_machineno.text= String.format("Machine no. %s",storerequestList[holder.adapterPosition].machinenumber)
        holder.closed_requested_createdate.text= String.format("Created Date: %s, %s",storerequestList[holder.adapterPosition].request_date,
            storerequestList[holder.adapterPosition].request_time)
       /* holder.closed_requested_completedate.text= String.format("Created Date: %s,%s",storerequestList[holder.adapterPosition].request_date,
            storerequestList[holder.adapterPosition].request_time)
*/
        holder.closed_requested_suggestedpart.text= String.format("Suggested part %s",storerequestList[holder.adapterPosition].suggested_part)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, StorePartDetailActivity::class.java)
            intent.putExtra("request_id",(storerequestList[holder.adapterPosition].id).toString())
            mContext!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return storerequestList.size
    }
    interface OnItemClick {
        fun onSelect(position: Int)
    }
    fun addAllStoreRequest(storeRequest: List<StoreRequest>) {
        storerequestList.clear()
        notifyDataSetChanged()
        storerequestList.addAll(storeRequest)
        notifyDataSetChanged()
    }

}