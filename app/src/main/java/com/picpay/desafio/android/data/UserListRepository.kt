package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface UserListRepository {
    suspend fun getUserList(): Flow<Result<List<UserResponse>>>
}

class UserListRepositoryImpl @Inject constructor(
    private val api: PicPayServiceHelperImpl
) : UserListRepository {

    override suspend fun getUserList(): Flow<Result<List<UserResponse>>> {
        return flow {
            val result = api.getUsers()
            val body = result.body()

            if (result.isSuccessful && body != null) {
                emit(Result.Success(body))
            } else {
                emit(Result.Failure(Exception("Error")))
            }
        }
        .catch { emit(Result.Failure(it)) }
        .flowOn(Dispatchers.IO)
    }
}