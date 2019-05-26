package com.appgenesis.com.maintenanceapp.operation_manager.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comment {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("maintenance_request_id")
    var maintenanceRequestId: String? = null
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("comment")
    var comment: String? = null
    @SerializedName("file")
    var file: Any? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("user")
    var user: User? = null

}
