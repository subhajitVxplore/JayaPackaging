package com.jaya.app.packaging.helpers_impl

import androidx.datastore.core.DataStore
import com.jaya.app.core.helpers.AppStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class AppStoreImpl @Inject constructor(
    private val prefs: DataStore<Preferences>
) : AppStore {
    override suspend fun intro(status: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun isIntroDone(): Boolean {
        TODO("Not yet implemented")
    }


}