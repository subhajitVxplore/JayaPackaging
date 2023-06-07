package com.jaya.app.packaging.module

import com.example.core.utils.AppNavigator
import com.jaya.app.packaging.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Singleton
    @Binds
    fun bindNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}