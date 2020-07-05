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
import com.example.basics.loginModule.db.LoginState
import com.example.basics.loginModule.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signoutBtn.setOnClickListener { onSignOut(it) }
        deleteUserBtn.setOnClickListener { onDelete(it) }
        observeModel()
        usernameTV.text = LoginState.user?.userName
    }

    private fun onDelete(it: View?) = viewModel.onDeleteUser()

    private fun onSignOut(it: View?) = viewModel.onSignOutUser()

    private fun observeModel() {
        viewModel.deleteUser.observe(viewLifecycleOwner, Observer {
            requireView().onSnackBar("User deleted success fully")
            Navigation.findNavController(requireView())
                .navigate(MainFragmentDirections.goToSignUp())
        })
        viewModel.signOutUser.observe(viewLifecycleOwner, Observer {
            requireView().onSnackBar("User sign out")
            Navigation.findNavController(requireView())
                .navigate(MainFragmentDirections.goToSignUp())
        })
    }
}