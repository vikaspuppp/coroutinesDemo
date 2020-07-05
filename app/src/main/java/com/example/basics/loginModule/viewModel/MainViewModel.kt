package com.example.basics.loginModule.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.basics.loginModule.db.LoginState
import com.example.basics.loginModule.db.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    val deleteUser by lazy { MutableLiveData<Boolean>() }
    val signOutUser by lazy { MutableLiveData<Boolean>() }

    fun onDeleteUser() {

        CoroutineScope(Dispatchers.IO).launch {
            UserDatabase(getApplication()).userDao().deleteUser(LoginState.user!!)
            withContext(Dispatchers.Main) {
                LoginState.logOut()
                deleteUser.value = true
            }
        }
    }

    fun onSignOutUser() {
        LoginState.logOut()
        signOutUser.value = true
    }
}