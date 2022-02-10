package com.example.parkingsystem.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.FragmentLoginBinding
import com.example.parkingsystem.utils.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystem.utils.LoadingDialog

class LoginFragment : Fragment(R.layout.fragment_login) {

    // kotlin delegated property
    private val binding : FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var viewModel: LoginViewModel

    // TODO: delete this when using binding with delegate
    // https://proandroiddev.com/viewbinding-with-kotlin-property-delegate-c907682e24c9

    // TODO: show errors with Toast or SnackBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // TODO: check why action bar is visible - done
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: check viewLifecycleOwner
        viewModel.viewState.observe(viewLifecycleOwner) {

            val loader = LoadingDialog(this)

            if (it.isLoading) {
                // show loading ProgressBar
                // TODO: check examples for loader - done
                loader.startLoading()
            }
            if (it.successLogin) {
                // TODO: Check why app is crashing after calling dismiss()
                //loader.dismiss()
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