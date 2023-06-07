package com.jaya.app.packaging.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jaya.app.core.helpers.AppStore
import com.jaya.app.packaging.helpers_impl.AppStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        private val Context.dataStore by preferencesDataStore("jayaPackaging")


//        private fun <T> provideApi(klass: Class<T>): T {
//            val okHttpClient = OkHttpClient.Builder().addInterceptor(
//                ChuckerInterceptor.Builder(JayaPackagingApp.app!!.applicationContext)
//                    .collector(ChuckerCollector(JayaPackagingApp.app!!.applicationContext))
//                    .maxContentLength(250000L)
//                    .redactHeaders(emptySet())
//                    .alwaysReadResponseBody(false)
//                    .build()
//            ).build()
//
//            return Retrofit.Builder()
//                .baseUrl(Metar[Constants.BASE_URL])
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//                .create(klass)
//        }
//
//
//        //-----------------------------------------------------------------------------------------------------
//        private fun <T> provideApi2(klass: Class<T>): T {
//            val okHttpClient = OkHttpClient.Builder().addInterceptor(
//                ChuckerInterceptor.Builder(JayaPackagingApp.app!!.applicationContext)
//                    .collector(ChuckerCollector(JayaPackagingApp.app!!.applicationContext))
//                    .maxContentLength(250000L)
//                    .redactHeaders(emptySet())
//                    .alwaysReadResponseBody(false)
//                    .build()
//            ).build()
//
//            return Retrofit.Builder()
//                .baseUrl("https://api.npoint.io/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//                .create(klass)
//        }



//        @Singleton
//        @Provides
//        fun provideAppVersion(): AppVersionApi = provideApi2(AppVersionApi::class.java)
//-------------------------------------------------------------------------------------
        @Singleton
        @Provides
        fun provideDataStorePreferences(@ApplicationContext appContext: Context): DataStore<Preferences> = appContext.dataStore


    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Binds
    fun bindAppStore(appStoreImpl: AppStoreImpl): AppStore



//-------------------------------------------------------------------------------------------

}