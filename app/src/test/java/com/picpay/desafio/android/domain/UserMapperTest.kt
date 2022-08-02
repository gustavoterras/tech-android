package com.picpay.desafio.android.domain

import com.picpay.desafio.android.mockUserList
import com.picpay.desafio.android.mockUserListResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserMapperTest {

    private lateinit var mapper: UserMapper

    @Before
    fun setup() {
        mapper = UserMapperImpl()
    }

    @Test
    fun `WHEN call toUserList THEN should return screen data properly`() = runTest {
        val result = mapper.toUserList(mockUserListResponse)
        assertEquals(mockUserList, result)
    }
}