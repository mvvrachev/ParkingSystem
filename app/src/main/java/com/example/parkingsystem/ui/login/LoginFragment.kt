package com.example.parkingsystem.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.FragmentLoginBinding
import com.example.parkingsystem.utils.viewBinding
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.utils.getSupportActionBar

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

            if(it.error.isNotEmpty()) {
                //TODO: check why toast and loader are showing at the same time after the second time the button is pressed
                Toast.makeText(context,it.error, Toast.LENGTH_SHORT).show()
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