package com.picpay.desafio.android.data

interface UserListRepository {
    suspend fun getUserList()
}

class UserListRepositoryImpl: UserListRepository {
    override suspend fun getUserList() {
        //TODO("Not yet implemented")
    }
}