package com.example.parkingsystem.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.FragmentLoginBinding
import com.example.parkingsystem.utils.viewBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    // kotlin delegated property
    private val binding : FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var viewModel: LoginViewModel

    // TODO: delete this when using binding with delegate
    // https://proandroiddev.com/viewbinding-with-kotlin-property-delegate-c907682e24c9

    // TODO: show errors with Toast or SnackBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: check why action bar is visible
        requireActivity().actionBar?.hide()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: check viewLifecycleOwner
        viewModel.viewState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                // show loading ProgressBar
                // TODO: check examples for loader
            }

            if (it.successLogin) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        with(binding) {
            registerButtonLoginPage.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            loginButton.setOnClickListener {
                val email = "q@mailinaor.com"
                val password = "123456"
                viewModel.doLogin(email, password)
            }
        }
    }
}