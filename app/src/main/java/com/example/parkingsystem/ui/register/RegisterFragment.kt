package com.example.parkingsystem.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.databinding.FragmentRegisterBinding
import com.example.parkingsystem.utils.getSupportActionBar
import com.example.parkingsystem.utils.viewBinding

class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private val binding : FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    private lateinit var viewModel: RegisterViewModel

    // TODO add back button go return to login screen
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSupportActionBar().hide()

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        viewModel.viewState.observe(viewLifecycleOwner) {
            loaderVisible(it.isLoading)

            if(it.successRegister) {
               findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            }
        }

        with(binding) {
            registerButton.setOnClickListener {
                viewModel.doRegister("username", "email@email.com", "CB2565AK", "ddsd")
            }
        }
    }
}