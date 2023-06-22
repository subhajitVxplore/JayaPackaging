package com.jaya.app.core.domain.useCases

import com.jaya.app.core.common.Data
import com.jaya.app.core.common.Destination
import com.jaya.app.core.common.EmitType
import com.jaya.app.core.helpers.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardUseCases @Inject constructor(
    private val appStore: AppStore
) {

    fun logOutFromDashboard() = flow {
        appStore.logout()
        emit(Data(type = EmitType.Navigate, Destination.Login))
    }




}