package com.example.basics.loginModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.basics.loginModule.viewModel.SignUpViewModel
import com.example.basics.R
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(SignUpViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupBtn.setOnClickListener { onSignUp(it) }
        gotoLoginBtn.setOnClickListener { onLoginPage(it) }
        observeModel()
    }

    private fun onLoginPage(it: View?) =
        it?.let { Navigation.findNavController(it).navigate(SignupFragmentDirections.goToLogin()) }

    private fun onSignUp(it: View?) = viewModel.signUp(
        signupUsername.text.toString(),
        signupPassword.text.toString(),
        otherInfo.text.toString()
    )

    private fun observeModel() {
        viewModel.signUpComplete.observe(
            viewLifecycleOwner,
            Observer {
                Navigation.findNavController(requireView())
                    .navigate(SignupFragmentDirections.moveToHome())
                requireView().onSnackBar("Sign-up success")
            })
        viewModel.signUpError.observe(viewLifecycleOwner, Observer { requireView().onSnackBar(it) })
    }

}

