package com.jaya.app.core.domain.models

data class AppVersionModel(
    val appVersion: AppVersion,
    val message: String,
    val status: Boolean
)

data class AppVersion(
    val isSkipable: Boolean,
    val link: String,
    val releaseDate: String,
    val versionCode: Int,
    val versionMessage: String
)