package com.picpay.desafio.android.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.RootDatabase
import com.picpay.desafio.android.data.dao.UserResponseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    fun provideUserResponseDao(root: RootDatabase): UserResponseDao = root.userResponseDao()

    @Provides
    @Singleton
    fun provideRootDatabase(@ApplicationContext appContext: Context): RootDatabase {
        return Room.databaseBuilder(
            appContext,
            RootDatabase::class.java,
            "app.db"
        ).fallbackToDestructiveMigration().build()
    }
}