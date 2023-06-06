package com.jaya.app.packaging.helpers_impl

import androidx.datastore.core.DataStore
import com.vxplore.core.helpers.AppStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences
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

    override suspend fun storeBaseUrl(url: String) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchBaseUrl(): String {
        TODO("Not yet implemented")
    }

    override suspend fun storeRegistrationStatus(userStatus: String) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchRegistrationStatus(): String {
        TODO("Not yet implemented")
    }

    override suspend fun login(userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun userId(): String {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isRegistered(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun storeFCMToken(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun lastFCMToken(): String {
        TODO("Not yet implemented")
    }

    override suspend fun removeLastFCMToken(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun savePrinter(macAddress: String) {
        TODO("Not yet implemented")
    }

    override suspend fun printer(): String? {
        TODO("Not yet implemented")
    }

}