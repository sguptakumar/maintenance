package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class TechnicianRequest(
     @SerializedName("id")
     val id: Int,
     @SerializedName("user_first_name")
     val firstname:String,
     @SerializedName("user_last_name")
     val lastname:String,
     @SerializedName("user_full_name")
     val fullname:String,
     @SerializedName("user_email")
     val email:String,
     @SerializedName("user_phone")
     val phone:String,
     @SerializedName("user_role")
     val role:String,
     @SerializedName("user_status")
     val status:String,
     @SerializedName("email_verified_at")
     val emailverified:String,
     @SerializedName("created_at")
     val createdat:String,
     @SerializedName("updated_at")
     val updatedat:String
)