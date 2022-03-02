package com.example.parkingsystem.ui.home

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.parkingsystem.data.ParkingSystemRepository
import com.example.parkingsystem.data.ParkingSystemRepositoryImpl

class HomeViewModel(private val repository: ParkingSystemRepository = ParkingSystemRepositoryImpl()) : ViewModel() {
    // TODO: Implement the ViewModel

    fun loadParkingSpaces() {
    }

}