package com.jaya.app.core.domain.models

data class BatchNoListModel(
    val batch_list: List<Batches>,
    val message: String,
    val status: Boolean
)

data class Batches(
    val batch_id: String,
    val batch_no: String
)