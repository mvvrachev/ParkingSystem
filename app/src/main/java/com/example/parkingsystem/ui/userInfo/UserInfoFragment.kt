package com.example.parkingsystem.ui.userInfo

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parkingsystem.R
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.databinding.FragmentHomeBinding
import com.example.parkingsystem.databinding.FragmentUserInfoBinding
import com.example.parkingsystem.models.UserInfo
import com.example.parkingsystem.ui.home.HomeViewModel
import com.example.parkingsystem.utils.viewBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserInfoFragment : BaseFragment(R.layout.fragment_user_info) {

    private val binding: FragmentUserInfoBinding by viewBinding(FragmentUserInfoBinding::bind)
    private lateinit var viewModel: UserInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]

        viewModel.viewState.observe(viewLifecycleOwner) {
            loaderVisible(it.isLoading)

            if(it.successLogout) {
                findNavController().navigate(R.id.action_userInfoFragment_to_loginFragment)
            }

            if(!it.successLogout && !it.isLoading) {
                Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
            }
        }

        var u: UserInfo
        val auth = Firebase.auth.currentUser
        val db = Firebase.firestore.collection("user-profiles").document(auth?.uid.toString())
        db.get().addOnSuccessListener { documentSnapshot ->
            Log.d(TAG, "Document data:${documentSnapshot.toObject<UserInfo>()}")
            u = requireNotNull(documentSnapshot.toObject())
        }

        with(binding) {
            email.text = auth?.email
//            carNumberInfo.text = user.carNumber
//            name.text = user.username



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