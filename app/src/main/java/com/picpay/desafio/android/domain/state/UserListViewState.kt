package com.picpay.desafio.android.domain.state

import com.picpay.desafio.android.domain.model.User

sealed interface UserListViewState {
    data class Success(val userList: List<User>) : UserListViewState
    object Error : UserListViewState
}