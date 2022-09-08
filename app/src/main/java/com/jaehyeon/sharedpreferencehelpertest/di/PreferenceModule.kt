package com.jaehyeon.sharedpreferencehelpertest.di

import android.content.Context
import com.jaehyeon.sharedpreferencehelpertest.repository.SharedPreferenceHelper
import com.jaehyeon.sharedpreferencehelpertest.repository.SharedPreferenceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext
        context: Context
    ): SharedPreferenceHelper = SharedPreferenceHelperImpl(context)


}