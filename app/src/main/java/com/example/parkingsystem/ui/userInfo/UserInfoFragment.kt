package com.example.parkingsystem.ui.userInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.parkingsystem.R
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.databinding.FragmentHomeBinding
import com.example.parkingsystem.databinding.FragmentUserInfoBinding
import com.example.parkingsystem.utils.viewBinding

class UserInfoFragment : BaseFragment(R.layout.fragment_user_info) {

    private val binding: FragmentUserInfoBinding by viewBinding(FragmentUserInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}