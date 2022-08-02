package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.dao.UserResponseDao
import com.picpay.desafio.android.data.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface UserListRepository {
    suspend fun getUserList(): Flow<Result<List<UserResponse>>>
    fun getUserListLocal(): List<UserResponse>?
    fun setUserListLocal(userList: List<UserResponse>)
}

class UserListRepositoryImpl @Inject constructor(
    private val api: PicPayServiceHelperImpl,
    private val dao: UserResponseDao
) : UserListRepository {

    override suspend fun getUserList(): Flow<Result<List<UserResponse>>> {
        return flow {
            var result = getUserListLocal()

            if (result.isNullOrEmpty()) {
                result = api.getUsers().body()
            }

            if (result != null) {
                setUserListLocal(result)
                emit(Result.Success(result))
            } else {
                emit(Result.Failure(Exception("Error")))
            }
        }
            .catch { emit(Result.Failure(it)) }
            .flowOn(Dispatchers.IO)
    }

    override fun getUserListLocal(): List<UserResponse>? = dao.getAll()

    override fun setUserListLocal(userList: List<UserResponse>) = dao.insertAll(userList)
}