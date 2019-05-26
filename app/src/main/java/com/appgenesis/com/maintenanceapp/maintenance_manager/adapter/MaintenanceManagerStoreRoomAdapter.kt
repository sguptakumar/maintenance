package com.appgenesis.com.maintenanceapp.maintenance_manager.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.appgenesis.com.maintenanceapp.R
import com.appgenesis.com.maintenanceapp.store.model.StoreListRequest
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.Requiredquantity




/**
 * Created by USER on 2/19/2019.
 */
class MaintenanceManagerStoreRoomAdapter(val mContext: Context?, val licenceList: ArrayList<StoreListRequest>)
    : RecyclerView.Adapter<MaintenanceManagerStoreRoomAdapter.MyViewHolder>() {
    private val partquantiy: List<Requiredquantity>? = null




    private var itemNumber = 0
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) ,View.OnClickListener  {
        override fun onClick(v: View?) {
           /* plus.setOnClickListener(View.OnClickListener {
                if(itemNumber>=0)
                    itemNumber+=1

                srequiredquantity.setText(itemNumber.toString())


            })
            minus.setOnClickListener(View.OnClickListener {
                if(itemNumber>0)
                    --itemNumber
                srequiredquantity.text= itemNumber.toString()
            })*/

        }

        val spart_name: CheckBox = view.findViewById<View>(R.id.spart_name) as CheckBox
        val savailable_quantity: TextView = view.findViewById<View>(R.id.savailable_quantity) as TextView
        val minus:ImageView=view.findViewById<View>(R.id.minus)as ImageView
        val srequiredquantity:TextView=view.findViewById<View>(R.id.srequired_quantity)as TextView
        val plus:ImageView=view.findViewById<View>(R.id.plus)as ImageView




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.store_room_item_layout, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.spart_name.text=licenceList[position].partname
        holder.savailable_quantity.text=licenceList[position].quantity
        val quantity:Requiredquantity =partquantiy!!.get(position)
        holder.plus.setTag(0)
         holder.plus.setOnClickListener(View.OnClickListener {

                 var value: Int = it.tag as Int
                    it.setTag(++value)

                 holder.srequiredquantity.setText(value.toString())

         })


         holder.minus.setOnClickListener(View.OnClickListener {
             var value: Int = holder.plus.tag as Int
             holder.plus.setTag(--value)
             holder.srequiredquantity.setText(value.toString())
         })

        holder.spart_name.setOnClickListener(View.OnClickListener {
            if (holder.spart_name.isChecked){
               val list= licenceList.get(position)
                val partname:String=list.partname
                val quantity:String=list.quantity

                Toast.makeText(mContext,"Item is checked",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(mContext,"Item is Unchecked",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getItemCount(): Int {
        return licenceList.size
    }
    fun addAlllistpart(storelist: List<StoreListRequest>) {
        licenceList.clear()
        notifyDataSetChanged()
        licenceList.addAll(storelist)
        notifyDataSetChanged()

    }
    }