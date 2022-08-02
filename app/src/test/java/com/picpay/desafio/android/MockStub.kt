package com.picpay.desafio.android

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.state.UserListViewState

val mockUserListResponse = arrayListOf(
    UserResponse(id = 1, name = "name", username = "username", img = "img"),
    UserResponse(id = 2, name = "name", username = "username", img = "img"),
    UserResponse(id = 3, name = "name", username = "username", img = "img"),
)

val mockUserList = arrayListOf(
    User(name = "name", username = "username", img = "img"),
    User(name = "name", username = "username", img = "img"),
    User(name = "name", username = "username", img = "img"),
)

val mockUserListResult = UserListViewState.Success(
    arrayListOf(
        User(name = "name", username = "username", img = "img"),
        User(name = "name", username = "username", img = "img"),
        User(name = "name", username = "username", img = "img"),
    )
)