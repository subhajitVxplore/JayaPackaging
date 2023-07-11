package com.jaya.app.packaging.repositoryImpls


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AddPackingDetailsModel
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.PackingLabourListModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.models.WorkersListModel
import com.jaya.app.core.domain.repositories.AddPackingDetailsRepository
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.packaging.module.MyApiList
import okhttp3.MultipartBody
import javax.inject.Inject

class AddPackingDetailsRepositoryImpl @Inject constructor(
    private val myApiList: MyApiList
) : AddPackingDetailsRepository {

    override suspend fun getAvailableWorkersList(): Resource<WorkersListModel> {
        return try {
            Resource.Success(myApiList.getAvailableWorkersList())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }

    override suspend fun getPackingLabourList(): Resource<PackingLabourListModel> {
        return try {
            Resource.Success(myApiList.getPackingLabourList())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }

    override suspend fun addPackingDetails(): Resource<AddPackingDetailsModel> {
        return try {
            Resource.Success(myApiList.addPackingDetails())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }


}

