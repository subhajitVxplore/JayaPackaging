package com.jaya.app.packaging.application

import android.app.Application
import com.jaya.app.packaging.utility.Metar
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JayaPackagingApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Metar.initialize(this)
        app = this
    }

    companion object {
       // lateinit var appInstance: JayaPackagingApp
        var app: JayaPackagingApp? = null
    }
}