package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.LoginModel


interface LoginRepository {

    suspend fun login(): Resource<LoginModel>
}