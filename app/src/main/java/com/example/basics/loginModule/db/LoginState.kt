package com.example.basics.loginModule.db

object LoginState {

    var isLogin = false
    var user: User? = null

    fun logOut() {
        isLogin = false
        user = null
    }

    fun login(user: User) {
        isLogin = true
        this.user = user
    }

}