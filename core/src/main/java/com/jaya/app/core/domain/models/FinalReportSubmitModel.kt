package com.jaya.app.core.domain.models

data class FinalReportSubmitModel(
    val isSubmitted: Boolean,
    val message: String,
    val status: Boolean
)