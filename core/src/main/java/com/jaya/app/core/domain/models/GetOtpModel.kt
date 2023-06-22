package com.jaya.app.core.domain.models

data class GetOtpModel(
    val isUser: Boolean,
    val message: String,
    val otp: String,
    val userId: String,
    val status: Boolean
)