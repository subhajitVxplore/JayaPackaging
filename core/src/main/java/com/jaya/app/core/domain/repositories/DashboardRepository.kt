package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel


interface DashboardRepository {

    suspend fun getUserDetails(): Resource<UserDetailsModel>
    suspend fun getPackagingList(): Resource<RecentPackagingModel>
}