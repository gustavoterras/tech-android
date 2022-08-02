package com.picpay.desafio.android.data

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.dao.UserResponseDao
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.mockUserListResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserListRepositoryTest {

    @Mock
    lateinit var api: PicPayServiceHelper

    @Mock
    lateinit var dao: UserResponseDao

    private lateinit var repository: UserListRepository

    @Before
    fun setup() {
        repository = UserListRepositoryImpl(api, dao)
    }

    @Test
    fun `WHEN call getUserList THEN should return list of values`() = runTest {
        whenever(api.getUsers()).thenReturn(Response.success(mockUserListResponse))
        whenever(dao.getAll()).thenReturn(emptyList())

        var result: Flow<Result<List<UserResponse>>>? = null
        val collectJob = launch(UnconfinedTestDispatcher()) {
             result = repository.getUserList()
        }

        verify(dao).getAll()
        verify(api).getUsers()

        Assert.assertEquals(flowOf(mockUserListResponse), result)

        collectJob.cancel()
    }

    @Test
    fun `WHEN call getUserList THEN should return error`() = runTest {
        whenever(api.getUsers()).thenReturn(Response.error(400, ResponseBody.create(null, "error")))
        whenever(dao.getAll()).thenReturn(emptyList())

        var result: Flow<Result<List<UserResponse>>>? = null
        val collectJob = launch(UnconfinedTestDispatcher()) {
            result = repository.getUserList()
        }

        verify(dao).getAll()
        verify(api).getUsers()

        val error = Result.Failure<Exception>(Exception("error"))
        Assert.assertEquals(flowOf(error), result)

        collectJob.cancel()
    }

    @Test
    fun `WHEN call getUserList AND the data persists THEN should return list of values`() = runTest {
        whenever(dao.getAll()).thenReturn(mockUserListResponse)

        var result: Flow<Result<List<UserResponse>>>? = null
        val collectJob = launch(UnconfinedTestDispatcher()) {
            result = repository.getUserList()
        }

        verify(dao).getAll()

        Assert.assertEquals(flowOf(mockUserListResponse), result)

        collectJob.cancel()
    }
}