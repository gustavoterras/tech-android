package com.picpay.desafio.android.domain

interface UserMapper {
    suspend fun toUserContactList()
}

class UserMapperImpl : UserMapper {
    override suspend fun toUserContactList() {
        //TODO("Not yet implemented")
    }
}