package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.UserListRepository
import com.picpay.desafio.android.data.UserListRepositoryImpl
import com.picpay.desafio.android.domain.UserListUseCase
import com.picpay.desafio.android.domain.UserListUseCaseImpl
import com.picpay.desafio.android.domain.UserMapper
import com.picpay.desafio.android.domain.UserMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class UserListModule {

    @Binds
    abstract fun userListRepository(userListRepository: UserListRepositoryImpl): UserListRepository

    @Binds
    abstract fun userListUseCase(userListUseCase: UserListUseCaseImpl): UserListUseCase

    @Binds
    abstract fun userMapper(userMapper: UserMapperImpl): UserMapper
}