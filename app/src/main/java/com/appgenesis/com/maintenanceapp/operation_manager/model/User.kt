package com.appgenesis.com.maintenanceapp.operation_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("user_first_name")
    @Expose
    var userFirstName: String? = null
    @SerializedName("user_last_name")
    @Expose
    var userLastName: String? = null
    @SerializedName("user_full_name")
    @Expose
    var userFullName: String? = null
    @SerializedName("user_email")
    @Expose
    var userEmail: String? = null
    @SerializedName("user_phone")
    @Expose
    var userPhone: String? = null
    @SerializedName("user_role")
    @Expose
    var userRole: String? = null
    @SerializedName("user_status")
    @Expose
    var userStatus: String? = null
    @SerializedName("user_image")
    @Expose
    var userImage: Any? = null
    @SerializedName("email_verified_at")
    @Expose
    var emailVerifiedAt: Any? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}
