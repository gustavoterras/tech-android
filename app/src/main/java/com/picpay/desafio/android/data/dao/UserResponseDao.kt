package com.picpay.desafio.android.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Transaction
import androidx.room.OnConflictStrategy
import com.picpay.desafio.android.data.model.UserResponse

@Dao
interface UserResponseDao {

    @Transaction
    @Query("SELECT * FROM UserResponse")
    fun getAll(): List<UserResponse>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserResponse>)
}
