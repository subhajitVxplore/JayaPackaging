package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel


interface SplashRepository {
    suspend fun appVersion(currentVersion: Int): Resource<AppVersionModel>

    suspend fun baseUrl(): Resource<BaseUrlModel>
}