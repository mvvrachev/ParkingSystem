package com.example.parkingsystem.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.base.BaseFragment
import com.example.parkingsystem.databinding.FragmentHomeBinding
import com.example.parkingsystem.models.ParkingSpace
import com.example.parkingsystem.utils.getSupportActionBar
import com.example.parkingsystem.utils.viewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var viewModel: HomeViewModel
//    private lateinit var adapter: ParkingSpacesAdapter
    private var adapter = ParkingSpacesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Use extension func
        //getSupportActionBar().hide()
        getSupportActionBar().show()
        getSupportActionBar().setDisplayHomeAsUpEnabled(false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.parkingSpaces.observe(viewLifecycleOwner) {
            binding.refresher.isRefreshing = it.isLoading
            adapter.setData(it.data)
            showError(it.error)
        }

        with(binding) {
            // if RV blinks disable animation
            parkingSpaces.adapter = adapter
            parkingSpaces.setHasFixedSize(true)
            refresher.setOnRefreshListener {
                viewModel.loadParkingSpaces()
            }

            callSecurityGuard.setOnClickListener {
                // Check whether a permission is needed to dial numbers
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:1234567890")
                startActivity(intent)
            }
        }
    }
}
