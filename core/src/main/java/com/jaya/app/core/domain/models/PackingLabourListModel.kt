package com.jaya.app.core.domain.models

data class PackingLabourListModel(
    val message: String,
    val packing_labour_list: List<PackingLabour>,
    val status: Boolean
)

data class PackingLabour(
    val packing_labour_id: String,
    val packing_labour_name: String
)