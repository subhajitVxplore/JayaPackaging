package com.jaya.app.packaging.module

import com.jaya.app.core.domain.models.AddPackingDetailsModel
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.AvailablePlantShiftModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.BatchNoListModel
import com.jaya.app.core.domain.models.FinalReportSubmitModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.LoginModel
import com.jaya.app.core.domain.models.PackingLabourListModel
import com.jaya.app.core.domain.models.PlantListModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.RunningShiftListModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.models.VerifyOtpModel
import com.jaya.app.core.domain.models.WorkersListModel
import okhttp3.MultipartBody
import retrofit2.http.*

interface MyApiList {

    @GET("9f5b17a1fdfc630319cf")
    suspend fun getBaseUrl(): BaseUrlModel

    @GET("dbc734947dc2b6deb690")
    suspend fun getAppVersion(): AppVersionModel

    @GET("e3e6637118816ba631ca")
    suspend fun getOtp(): GetOtpModel

    @GET("e3e6637118816ba631ca")
    suspend fun login(): LoginModel

    @GET("16549f6d32a350455f94")
    suspend fun verifyOtp(): VerifyOtpModel

    @GET("b67e7dc541c962d4707e")
    suspend fun getUserDetails(): UserDetailsModel

//    @GET("30f115b34c98e47c8ec9")
//    suspend fun getPackagingList(): RecentPackagingModel

    @GET("eff18e1aec9cc5e3edb8")
    suspend fun getRunningShiftList(): RunningShiftListModel

    @GET("73413359935d2cab6f44")
    suspend fun getAvailablePlantShiftList(): AvailablePlantShiftModel

    @GET("d1e20e6edb0b9a3a8e11")
    suspend fun getAvailableWorkersList(): WorkersListModel

    @GET("d0de5e7bea81aac26729")
    suspend fun getPackingLabourList(): PackingLabourListModel

    @GET("ee6a6917bee1f42a44e5")
    suspend fun getPlants(): PlantListModel

    @GET("fb46a8f5ea514a7f6538")
    suspend fun getBatches(): BatchNoListModel

    @GET("61acf630747f10fb04da")
    suspend fun getProductTypes(): ProductTypesModel

    @GET("87e9ad8f2bd0dc6bf02e")
    suspend fun addProduct(): AddProductModel

    @GET("414cac3af7e41d0cc0aa")
    suspend fun addPackingDetails(): AddPackingDetailsModel

    @GET("d084b99f3b31724300e1")
    suspend fun submitFinalReport(): FinalReportSubmitModel

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
        @Part video_clip_list: List<MultipartBody.Part>
        // @Part("video_clip_list") videoFiles:List<MultipartBody.Part>
    ): AddProductModel


}