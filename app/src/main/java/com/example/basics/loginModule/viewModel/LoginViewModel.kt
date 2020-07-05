package com.example.basics.loginModule.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.basics.loginModule.db.LoginState
import com.example.basics.loginModule.db.User
import com.example.basics.loginModule.db.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    val loginComplete by lazy { MutableLiveData<Boolean>() }
    val loginError by lazy { MutableLiveData<String>() }

    fun login(userName: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = UserDatabase(getApplication()).userDao().getUser(userName, password)
            withContext(Dispatchers.Main) {
                if (user!= null) {
                    loginComplete.value = true
                    LoginState.login(user)
                } else {
                    loginError.value = "User not found in db."
                }
            }
        }
    }
}