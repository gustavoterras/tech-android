package com.picpay.desafio.android.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class UserResponse(
    @ColumnInfo(name = "img")
    @Json(name = "img")
    val img: String,

    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "username")
    @Json(name = "username")
    val username: String
) : Parcelable