package com.jaya.app.core.domain.repositories


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.BatchNoListModel


interface ProductionReportRepository {

    suspend fun getBatches(): Resource<BatchNoListModel>

}