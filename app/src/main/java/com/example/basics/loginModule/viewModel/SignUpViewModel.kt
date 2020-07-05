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

class SignUpViewModel(app: Application) : AndroidViewModel(app) {

    val signUpComplete by lazy { MutableLiveData<Boolean>() }
    val signUpError by lazy { MutableLiveData<String>() }

    fun signUp(userName: String, password: String, info: String) {
        if (userName.isEmpty() || password.isEmpty() || info.isEmpty()) {
            signUpError.value = " Please fill all detail."
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            val user = User(userName, password)
            user.info = info
            val userId = UserDatabase(getApplication()).userDao().addUser(user)
            user.id = userId.toInt()
            LoginState.login(user)
            withContext(Dispatchers.Main) {
                signUpComplete.value = true
            }
        }
    }

}