package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User
import javax.inject.Inject

interface UserMapper {
    suspend fun toUserList(userListResponse: List<UserResponse>): List<User>
}

class UserMapperImpl @Inject constructor() : UserMapper {
    override suspend fun toUserList(userListResponse: List<UserResponse>): List<User> {
        return userListResponse.map { userResponse ->
            User(
                img = userResponse.img,
                name = userResponse.name,
                username = userResponse.username
            )
        }
    }
}