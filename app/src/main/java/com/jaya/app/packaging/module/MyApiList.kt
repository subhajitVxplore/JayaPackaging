package com.jaya.app.packaging.module

import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import retrofit2.http.*


interface MyApiList {

    @GET("9f5b17a1fdfc630319cf")
    suspend fun getBaseUrl(): BaseUrlModel

    @GET("dbc734947dc2b6deb690")
    suspend fun getAppVersion(): AppVersionModel


}