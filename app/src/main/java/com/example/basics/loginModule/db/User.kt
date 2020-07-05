package com.example.basics.loginModule.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val userName: String, val password: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var info: String? = null

}