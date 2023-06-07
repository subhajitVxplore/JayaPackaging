package com.jaya.app.core.helpers

interface AppStore {


    suspend fun intro(status: Boolean)
    suspend fun isIntroDone(): Boolean

}

