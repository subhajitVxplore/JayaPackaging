package com.jaya.app.core.domain.models

data class RecentPackagingModel(
    val message: String,
    val packaging_list: List<Packaging>,
    val plant: String,
    val shift: String,
    val status: Boolean
)

data class Packaging(
    val batch_no: String,
    val product_name: String,
    val time_stamp: String,
    val video_link: String
)