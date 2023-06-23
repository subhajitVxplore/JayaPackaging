package com.jaya.app.packaging.repositoryImpls


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.packaging.module.MyApiList
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val myApiList: MyApiList
) : DashboardRepository {

    override suspend fun getUserDetails(): Resource<UserDetailsModel> {
        return try {
            Resource.Success(myApiList.getUserDetails())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }

    override suspend fun getPackagingList(): Resource<RecentPackagingModel> {
        return try {
            Resource.Success(myApiList.getPackagingList())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }


}

