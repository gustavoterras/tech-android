package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.Result
import com.picpay.desafio.android.data.UserListRepository
import com.picpay.desafio.android.domain.state.UserListViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface UserListUseCase {
    suspend fun getUserList(viewModelScope: CoroutineScope): StateFlow<UserListViewState>
}

class UserListUseCaseImpl @Inject constructor(
    private val repository: UserListRepository,
    private val mapper: UserMapper
) : UserListUseCase {
    override suspend fun getUserList(
        viewModelScope: CoroutineScope
    ): StateFlow<UserListViewState> {
        return repository.getUserList().map { result ->
            when(result) {
                is Result.Success -> UserListViewState.Success(mapper.toUserList(result.data))
                is Result.Failure -> UserListViewState.Error
            }
        }.stateIn(scope = viewModelScope)
    }
}