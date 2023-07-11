package com.jaya.app.packaging.repositoryImpls


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.FinalReportSubmitModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.domain.repositories.FinalReportRepository
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.packaging.module.MyApiList
import okhttp3.MultipartBody
import javax.inject.Inject

class FinalReportRepositoryImpl @Inject constructor(
    private val myApiList: MyApiList
) : FinalReportRepository {

    override suspend fun submitFinalReport(): Resource<FinalReportSubmitModel> {
        return try {
            Resource.Success(myApiList.submitFinalReport())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }


}

