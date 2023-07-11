package com.jaya.app.packaging.repositoryImpls


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.BatchNoListModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.ProductionReportRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.packaging.module.MyApiList
import okhttp3.MultipartBody
import javax.inject.Inject

class ProductionReportRepositoryImpl @Inject constructor(
    private val myApiList: MyApiList
) : ProductionReportRepository {

    override suspend fun getBatches(): Resource<BatchNoListModel> {
        return try {
            Resource.Success(myApiList.getBatches())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }


}

