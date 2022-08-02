package com.picpay.desafio.android.domain

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.Result
import com.picpay.desafio.android.data.UserListRepository
import com.picpay.desafio.android.domain.state.UserListViewState
import com.picpay.desafio.android.mockUserList
import com.picpay.desafio.android.mockUserListResponse
import com.picpay.desafio.android.mockUserListResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserListUseCaseTest {

    @Mock
    lateinit var repository: UserListRepository

    @Mock
    lateinit var mapper: UserMapper

    private lateinit var useCase: UserListUseCase

    @Before
    fun setup() {
        useCase = UserListUseCaseImpl(repository, mapper)
    }

    @Test
    fun `WHEN call getUserList THEN should return UserListViewState Success`() =
        runTest(UnconfinedTestDispatcher()) {
            whenever(repository.getUserList()).thenReturn(
                flowOf(Result.Success(mockUserListResponse))
            )

            whenever(mapper.toUserList(mockUserListResponse)).thenReturn(mockUserList)

            val result = useCase.getUserList(this)

            verify(repository).getUserList()
            verify(mapper).toUserList(mockUserListResponse)
            assertEquals(mockUserListResult, result.value)
        }

    @Test
    fun `WHEN call getUserList THEN should return UserListViewState Error`() =
        runTest(UnconfinedTestDispatcher()) {
            whenever(repository.getUserList()).thenReturn(
                flowOf(Result.Failure(Exception("error")))
            )

            val result = useCase.getUserList(this)

            verify(repository).getUserList()
            assertEquals(UserListViewState.Error, result.value)
        }
}