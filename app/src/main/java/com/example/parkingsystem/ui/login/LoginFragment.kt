package com.example.parkingsystem.ui.login

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.FragmentLoginBinding
import com.example.parkingsystem.utils.viewBinding
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.models.ParkingSpace
import com.example.parkingsystem.utils.getSupportActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    // kotlin delegated property
    private val binding : FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var viewModel: LoginViewModel


    // TODO: show errors with Toast or SnackBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSupportActionBar().hide()

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: check viewLifecycleOwner
        viewModel.viewState.observe(viewLifecycleOwner) {
            loaderVisible(it.isLoading)

            if (it.successLogin) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }

            if(!it.successLogin && !it.isLoading) {
                Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
            }
        }

        with(binding) {

            registerButtonLoginPage.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            loginButton.setOnClickListener {
                val email = binding.emailEditTextLogin.text.toString()
                val password = binding.passwordEditTextLogin.text.toString()
                viewModel.doLogin(email, password)
            }
        }


    }

}