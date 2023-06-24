package com.jaya.app.packaging.module

import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.models.VerifyOtpModel
import okhttp3.MultipartBody
import retrofit2.http.*

interface MyApiList {

    @GET("9f5b17a1fdfc630319cf")
    suspend fun getBaseUrl(): BaseUrlModel

    @GET("dbc734947dc2b6deb690")
    suspend fun getAppVersion(): AppVersionModel

    @GET("e3e6637118816ba631ca")
    suspend fun getOtp(): GetOtpModel

    @GET("16549f6d32a350455f94")
    suspend fun verifyOtp(): VerifyOtpModel

    @GET("b67e7dc541c962d4707e")
    suspend fun getUserDetails(): UserDetailsModel

    @GET("30f115b34c98e47c8ec9")
    suspend fun getPackagingList(): RecentPackagingModel

    @GET("61acf630747f10fb04da")
    suspend fun getProductTypes(): ProductTypesModel

    @GET("87e9ad8f2bd0dc6bf02e")
    suspend fun addProduct(): AddProductModel

    @Multipart
    @POST("add_packing_data/{user_id}")
    suspend fun submitPackingDetails(
        @Path("user_id") user_id: String,
        @Field("product_name") product_name: String,
        @Field("packing_name") packing_name: String,
        @Field("batch_number") batch_number: String,
        @Field("product_type") product_type: String,
        @Field("start_time") start_time: String,
        @Field("end_time") end_time: String,
        @Part video_clip_list:List<MultipartBody.Part>
       // @Part("video_clip_list") videoFiles:List<MultipartBody.Part>
    ): AddProductModel


}