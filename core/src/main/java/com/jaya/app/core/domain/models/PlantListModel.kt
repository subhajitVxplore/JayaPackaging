package com.jaya.app.core.domain.models

data class PlantListModel(
    val message: String,
    val plant_list: List<Plant>,
    val status: Boolean
)

data class Plant(
    val plant_id: String,
    val plant_no: String
)