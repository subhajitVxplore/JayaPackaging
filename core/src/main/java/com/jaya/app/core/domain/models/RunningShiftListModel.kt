package com.jaya.app.core.domain.models

data class RunningShiftListModel(
    val message: String,
    val running_shift_list: List<RunningShift>,
    val status: Boolean
)

data class RunningShift(
    val date: String,
    val image_count: String,
    val mixing_supervisor: String,
    val packing_supervisor: String,
    val plant: String,
    val product_type: String,
    val product_type_id: String,
    val shift: String,
    val video_count: String
)