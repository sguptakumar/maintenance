package com.appgenesis.com.maintenanceapp.network

import com.appgenesis.com.maintenanceapp.maintenance_manager.model.CustomOrder
import com.appgenesis.com.maintenanceapp.maintenance_manager.model.ServerResponse
import com.appgenesis.com.maintenanceapp.model.*
import com.appgenesis.com.maintenanceapp.operation_manager.model.BreakdownRequestResponse
import com.appgenesis.com.maintenanceapp.store.model.StoreListResponse
import com.appgenesis.com.maintenanceapp.store.model.StoreReuestListResponse
import com.appgenesis.com.maintenanceapp.store.model.StorerequestdetailResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Path

interface NetworkApi {
    @POST("/maintenance/api/v1/auth/signup")
    fun doRegistration(@HeaderMap headers: Map<String, String>, @Body userRegistration: UserRegistration): Single<MainResponseModel>

    @POST("/maintenance/api/v1/auth/login")
    fun loginUser(@HeaderMap headers: Map<String, String>, @Body userRequest: UserRequest): Observable<LoginResponseModel>

    @POST("/maintenance/api/v1/auth/password/resetRequest")
    fun forgotPassword(@HeaderMap headers: Map<String, String>, @Body forgotPasswordRequest: ForgotPasswordRequest): Observable<MainResponseModel>

    @POST("/maintenance/api/v1/auth/password/reset")
    fun resetPassword(@HeaderMap headers: Map<String, String>, @Body forgotPasswordRequest: ForgotPasswordRequest): Observable<MainResponseModel>

    @GET("/maintenance/api/v1/auth/logout")
    fun logoutUser(@HeaderMap headers: Map<String, String>): Observable<MainResponseModel>

    @GET("/maintenance/api/v1/user")
    fun getUserProfile(@HeaderMap headers: Map<String, String>): Observable<MainResponseModel>

    @GET("/maintenance/api/v1/breakdownRequest")
    fun getAllBreakdownRequest(@Query("filterDate") filterDate: String, @HeaderMap headers: Map<String, String>): Observable<BreakdownListResponse>
    @GET("/maintenance/api/v1/maintenanceRequest")
    fun getAssignMaintenanceRequest(@Query("filterDate")
                                          filterDate: String, @Query("request_status")
    requestStatus: String ,@HeaderMap headers: Map<String, String>):
            Observable<BreakdownListResponse>
    @GET("/maintenance/api/v1/preventiveRequest")
    fun getAssignPreventiveRequest(@Query("filterDate")
                                    filterDate: String, @Query("request_status")
                                    requestStatus: String ,@HeaderMap headers: Map<String, String>):
            Observable<BreakdownListResponse>

    @GET("/maintenance/api/v1/breakdownRequest")
    fun getAssignMaintenanceBreakdownRequest(@Query("filterDate")
                                             filterDate: String, @Query("request_status")
                                             requestStatus: String ,@HeaderMap headers: Map<String, String>):
            Observable<BreakdownListResponse>



    @GET("/maintenance/api/v1/breakdownRequest/{id}")
    fun getBreakdownRequestDetails(@HeaderMap headers: Map<String, String>,@Path("id")id: String): Observable<BreakdownRequestResponse>

    @POST("/maintenance/api/v1/breakdownRequest/create")
    fun createBreakdownRequest(@HeaderMap headers: Map<String, String>,@Body breakdownRequest: CreateBreakdownRequest): Observable<MainResponseModel>

    @GET("/maintenance/api/v1/maintenanceRequest")
    fun getAllMaintenanceRequest(@Query("filterDate")filterDate: String,@HeaderMap headers: Map<String, String>) :Observable<BreakdownListResponse>

    @POST("/maintenance/api/v1/preventiveRequest/create")
    fun createPreventiveRequest(@HeaderMap headers: Map<String, String>,@Body breakdownRequest: CreateBreakdownRequest): Observable<MainResponseModel>
    @POST("/maintenance/api/v1/maintenanceRequest/create")
    fun createMaintenanceRequest(@HeaderMap headers: Map<String, String>,@Body breakdownRequest: CreateBreakdownRequest): Observable<MainResponseModel>


    @GET("/maintenance/api/v1/preventiveRequest")
    fun getAllPreventiveRequest(@Query("filterDate") filterDate: String, @HeaderMap headers: Map<String, String>): Observable<BreakdownListResponse>

    @POST("/maintenance/api/v1/technician/create")
    fun createTechnicianRequest(@HeaderMap headers: Map<String, String>, @Body technicianInfo: CreateTechnician): Observable<MainResponseModel>

    @GET("/maintenance/api/v1/technician")
    fun getAllTechnician(@HeaderMap headers: Map<String, String>): Observable<TechnicianListResponse>
    @POST("/maintenance/api/v1/order/customPart")
    fun createCustomOrder(@HeaderMap headers: Map<String, String>, @Body customOrder: CustomOrder): Observable<MainResponseModel>
    @GET("/maintenance/api/v1/store/request")
    fun getStoreRequest(@HeaderMap headers: Map<String, String>):Observable<StoreReuestListResponse>
    @GET("/maintenance/api/v1/store/request/{id}")
    fun getStoreRequestDetail(@HeaderMap headers: Map<String, String>,@Path("id")id: String):Observable<StorerequestdetailResponse>
    @PUT("/maintenance/api/v1/breakdownRequest/assignTechnician/{id}")
    fun AssignTechnician(@HeaderMap headers: Map<String, String>,@Path("id")id: Int, @Body assigntechnician: AssignTechnician):Observable<MainResponseModel>
    @POST("/maintenance/api/v1/upload/base64Image")
    fun uploadFile(@HeaderMap headers: Map<String, String>,@Body uploadObject: UploadObject):Observable<Uploadimage>
    @POST("/maintenance/api/v1/comment")
    fun postcomment(@HeaderMap headers: Map<String, String>, @Body commmentpost:CommentPost):Observable<MainResponseModel>
    @GET("/maintenance/api/v1/maintenanceRequest/{id}")
    fun getMaintenanceRequestDetails(@HeaderMap headers: Map<String, String>,@Path("id")id: String):Observable<BreakdownRequestResponse>
    @GET("/maintenance/api/v1/preventiveRequest/{id}")
    fun getPreventiveRequestDetails(@HeaderMap headers: Map<String, String>,@Path("id")id: String):Observable<BreakdownRequestResponse>
    @GET("/maintenance/api/v1/store")
    fun getStorelistpart(@HeaderMap headers: Map<String, String>):Observable<StoreListResponse>
    @Multipart
    @POST("/maintenance/api/v1/upload/image")
    fun uploadmultipartfile(@HeaderMap headers: Map<String, String>, @Part("image\"; filename=\"myfile.jpg\"") image: MultipartBody.Part, @Part("file") name:RequestBody): Call<ServerResponse>

}