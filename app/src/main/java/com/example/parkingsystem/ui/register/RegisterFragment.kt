package com.example.parkingsystem.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystem.R
import com.example.parkingsystem.databinding.FragmentRegisterBinding
import com.example.parkingsystem.utils.viewBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding : FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

    }
}