package com.jaya.app.packaging.module

import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.models.VerifyOtpModel
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


}