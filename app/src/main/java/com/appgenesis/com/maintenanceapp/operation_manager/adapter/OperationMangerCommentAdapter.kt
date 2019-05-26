package com.appgenesis.com.maintenanceapp.operation_manager.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.operation_manager.model.Comment
import com.appgenesis.com.maintenanceapp.operation_manager.model.CommentList
import de.hdodenhof.circleimageview.CircleImageView

class OperationMangerCommentAdapter(val mContext: Context?, val licenceList: ArrayList<Comment>) : RecyclerView.Adapter<OperationMangerCommentAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image=view.findViewById<View>(R.id.profile)as CircleImageView
        val name=view.findViewById<View>(R.id.name)as TextView
        val date=view.findViewById<View>(R.id.date)as TextView
        val comment=view.findViewById<View>(R.id.comment)as TextView

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.comment_item_layout, parent, false)
        return MyViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.comment.text=licenceList[holder.adapterPosition].comment
        holder.name.text=licenceList[holder.adapterPosition].user!!.userFullName

    }

    override fun getItemCount(): Int {
        return licenceList.size
    }
    fun addAllComment(comment: List<Comment>?) {
        licenceList.clear()
        notifyDataSetChanged()
        if (comment != null) {
            licenceList.addAll(comment)
        }
        notifyDataSetChanged()


    }
}