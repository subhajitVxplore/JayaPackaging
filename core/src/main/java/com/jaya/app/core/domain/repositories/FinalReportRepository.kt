package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.FinalReportSubmitModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import okhttp3.MultipartBody


interface FinalReportRepository {

    suspend fun submitFinalReport(): Resource<FinalReportSubmitModel>

//    suspend fun submitPackingDetails(
//        user_id: String,
//        product_name: String,
//        packing_name: String,
//        batch_number: String,
//        product_type: String,
//        start_time: String,
//        end_time: String,
//        video_clip_list:List<MultipartBody.Part>
//    ): Resource<AddProductModel>

}