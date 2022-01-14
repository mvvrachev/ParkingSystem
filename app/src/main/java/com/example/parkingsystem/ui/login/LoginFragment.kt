package com.example.parkingsystem.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.parkingsystem.R

class LoginFragment : Fragment(R.layout.login_fragment) {

    companion object {
        // TODO check newInstance fragment
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    // TODO: show errors with Toast or SnackBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: check viewLifecycleOwner
        viewModel.viewState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                // show loading ProgressBar
            }
        }
        view.findViewById<Button>(R.id.login).setOnClickListener {
            val email = "q@mailinaor.com"
            val password = "123456"
            viewModel.doLogin(email, password)
        }
    }
}