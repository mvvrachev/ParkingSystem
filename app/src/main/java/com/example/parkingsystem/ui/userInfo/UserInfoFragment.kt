package com.example.parkingsystem.ui.userInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.databinding.FragmentUserInfoBinding
import com.example.parkingsystem.models.User
import com.example.parkingsystem.utils.DatesHelper.getTodayDate
import com.example.parkingsystem.utils.viewBinding

class UserInfoFragment : BaseFragment(R.layout.fragment_user_info) {

    private val binding: FragmentUserInfoBinding by viewBinding(FragmentUserInfoBinding::bind)
    private lateinit var viewModel: UserInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]
        viewModel.fetchUserInfo()

        viewModel.userInfoViewState.observe(viewLifecycleOwner) {
            loaderVisible(it.isLoading)

            with(binding) {
                name.text = it.userData.username
                email.text = it.userData.email
                carNumberInfo.text = getString(R.string.your_car_number, it.userData.carNumber)
                date.text = getString(R.string.today_is, getTodayDate())
            }

            showError(it.error)
        }

        viewModel.logoutViewState.observe(viewLifecycleOwner) {
            loaderVisible(it.isLoading)

            if(it.successLogout) {
                findNavController().navigate(R.id.action_userInfoFragment_to_loginFragment)
            }

            if(!it.successLogout && !it.isLoading) {
                showError(it.error)
            }
        }

        with(binding) {
            logout.setOnClickListener {
                viewModel.doLogout()
            }

            callParking.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse(requireContext().getString(R.string.securityPhoneNumber))
                startActivity(intent)
            }
        }
    }
}