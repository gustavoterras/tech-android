package com.picpay.desafio.android.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.UserListUseCase
import com.picpay.desafio.android.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    application: Application,
    private val useCase: UserListUseCase
) : AndroidViewModel(application) {

    private val _userListState = MutableStateFlow<UserListViewState>(UserListViewState.Success(emptyList()))
    val userListState: StateFlow<UserListViewState> get() = _userListState

    init {
        request()
    }

    private fun request() {
        viewModelScope.launch {
            _userListState.value = useCase.getUserList(this).value
        }
    }

    sealed interface UserListViewState {
        data class Success(val userList: List<User>) : UserListViewState
        object Error : UserListViewState
    }
}