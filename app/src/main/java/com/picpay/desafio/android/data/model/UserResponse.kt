package com.picpay.desafio.android.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "img") val img: String,
    @Json(name = "name") val name: String,
    @Json(name = "id") val id: Int,
    @Json(name = "username") val username: String
) : Parcelable