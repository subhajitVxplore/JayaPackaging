package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.PackingLabourListModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.models.WorkersListModel
import okhttp3.MultipartBody


interface AddPackingDetailsRepository {

    suspend fun getAvailableWorkersList(): Resource<WorkersListModel>

    suspend fun getPackingLabourList(): Resource<PackingLabourListModel>


}