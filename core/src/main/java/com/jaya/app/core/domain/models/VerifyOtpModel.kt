package com.jaya.app.core.domain.models

data class VerifyOtpModel(
    val isMatched: Boolean,
    val message: String,
    val status: Boolean,
    val userId: String
)