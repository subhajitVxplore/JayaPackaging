package com.jaya.app.core.domain.models

data class WorkersListModel(
    val available_packers_number_list: List<AvailablePackersNumber>,
    val available_packing_mistri_list: List<AvailablePackingMistri>,
    val available_packing_operator_list: List<AvailablePackingOperator>,
    val message: String,
    val status: Boolean
)

data class AvailablePackingMistri(
    val packing_mistri_id: String,
    val packing_mistri_name: String
)

data class AvailablePackingOperator(
    val packing_operator_id: String,
    val packing_operator_name: String
)

data class AvailablePackersNumber(
    val packers_number: String,
    val packers_number_id: String
)