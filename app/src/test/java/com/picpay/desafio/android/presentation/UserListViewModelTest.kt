package com.picpay.desafio.android.presentation

import android.app.Application
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.MainDispatcherRule
import com.picpay.desafio.android.domain.UserListUseCase
import com.picpay.desafio.android.mockUserListResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var app: Application

    @Mock
    lateinit var useCase: UserListUseCase

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup() {
        viewModel = UserListViewModel(app, useCase)
    }

    @Test
    fun `WHEN viewModel is initialized THEN should call getUserList`() = runTest(UnconfinedTestDispatcher()) {
        val result = MutableStateFlow(mockUserListResult)
        whenever(useCase.getUserList(this)).thenReturn(result)

        verify(useCase).getUserList(this)

        Assert.assertEquals(result, viewModel.userListState)
    }
}