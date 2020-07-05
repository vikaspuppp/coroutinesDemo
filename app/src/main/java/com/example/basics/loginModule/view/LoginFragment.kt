package com.example.basics.loginModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.basics.R
import com.example.basics.loginModule.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gotoSignupBtn.setOnClickListener { onSignUp(it) }
        loginBtn.setOnClickListener { onLoginPage(it) }
        observeModel()
    }

    private fun onLoginPage(it: View?) =
        viewModel.login(loginUsername.text.toString(), loginPassword.text.toString())

    private fun onSignUp(it: View?) =
        it?.let { Navigation.findNavController(it).navigate(LoginFragmentDirections.goToSignUp()) }

    private fun observeModel() {
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer {
            requireView().onSnackBar("Welcome back!!  ${loginUsername.text.toString()}")
            Navigation.findNavController(requireView()).navigate(LoginFragmentDirections.goToHome())
        })
        viewModel.loginError.observe(viewLifecycleOwner, Observer {
            requireView().onSnackBar(it)
        })
    }

}

fun View.onSnackBar(msg: String) = Snackbar.make(this, msg, Snackbar.LENGTH_LONG).show()