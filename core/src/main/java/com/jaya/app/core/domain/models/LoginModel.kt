package com.jaya.app.core.domain.models

data class LoginModel(
    val isUser: Boolean,
    val message: String,
    val userId: String,
    val isMatched: Boolean,
    val status: Boolean
)