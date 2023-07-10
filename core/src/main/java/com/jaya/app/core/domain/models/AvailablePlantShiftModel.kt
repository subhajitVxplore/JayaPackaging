package com.jaya.app.core.domain.models

data class AvailablePlantShiftModel(
    val available_plant_shift_list: List<AvailablePlantShift>,
    val message: String,
    val status: Boolean
)

data class AvailablePlantShift(
    val plant: String,
    val shift: String,
    val date: String,
    val mixing_supervisor: String,
    val packing_supervisor: String,
    val product_type: String,
    val product_type_id: String
)