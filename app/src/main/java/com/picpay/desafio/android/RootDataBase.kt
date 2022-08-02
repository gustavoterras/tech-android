package com.picpay.desafio.android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.dao.UserResponseDao
import com.picpay.desafio.android.data.model.UserResponse

@Database(
    entities = [
        UserResponse::class
    ],
    version = RootDatabase.VERSION
)

abstract class RootDatabase : RoomDatabase() {
    abstract fun userResponseDao(): UserResponseDao

    companion object {
        const val VERSION = 1
    }
}