package com.picpay.desafio.android.domain

interface UserListUseCase {
    suspend fun getUserList()
}

class UserListUseCaseImpl : UserListUseCase {
    override suspend fun getUserList() {
        //TODO("Not yet implemented")
    }
}