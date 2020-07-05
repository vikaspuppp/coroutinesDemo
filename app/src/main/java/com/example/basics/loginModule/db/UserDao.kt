package com.example.basics.loginModule.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User): Long

    @Query("select * from user where userName = :userName AND password = :password")
    suspend fun getUser(userName: String, password: String): User

    @Delete
    suspend fun deleteUser(user: User)
}