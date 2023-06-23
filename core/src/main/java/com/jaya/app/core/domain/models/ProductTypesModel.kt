package com.jaya.app.core.domain.models

data class ProductTypesModel(
    val message: String,
    val product_type_list: List<ProductType>,
    val status: Boolean
)

data class ProductType(
    val product_type: String,
    val product_type_id: String
)