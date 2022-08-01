package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

interface PicPayServiceHelper {
    suspend fun getUsers(): Response<List<UserResponse>>
}

class PicPayServiceHelperImpl @Inject constructor(private val apiService: PicPayService) : PicPayServiceHelper {
    override suspend fun getUsers(): Response<List<UserResponse>> = apiService.getUsers()
}