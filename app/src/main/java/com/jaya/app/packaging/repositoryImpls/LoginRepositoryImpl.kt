package com.jaya.app.packaging.repositoryImpls


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.packaging.module.MyApiList
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val myApiList: MyApiList
) : LoginRepository {

    override suspend fun getOtp(): Resource<GetOtpModel> {
        return try {
            Resource.Success(myApiList.getOtp())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }


}

