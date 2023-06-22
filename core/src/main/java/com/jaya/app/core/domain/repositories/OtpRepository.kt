package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.VerifyOtpModel


interface OtpRepository {

    suspend fun verifyOtp(): Resource<VerifyOtpModel>
}