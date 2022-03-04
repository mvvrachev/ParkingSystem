package com.example.parkingsystem.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.data.ParkingSystemRepository
import com.example.parkingsystem.data.ParkingSystemRepositoryImpl
import com.example.parkingsystem.models.ParkingSpace
import com.example.parkingsystem.ui.login.LoginViewState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeViewModel(private val repository: ParkingSystemRepository = ParkingSystemRepositoryImpl()) : ViewModel() {
    // TODO: Implement the ViewModel

    //val parkingSpaces = mutableListOf<ParkingSpace>()
    private val _parkingSpaces: MutableLiveData<MutableList<ParkingSpace>> = MutableLiveData(mutableListOf())
    val parkingSpaces: LiveData<MutableList<ParkingSpace>> = _parkingSpaces

    fun loadParkingSpaces() {
        val db = Firebase.firestore.collection("parking-management-system").document("parking-spaces")
        db.get()
            .addOnSuccessListener { d ->
                    val space = d.toObject<ParkingSpace>()
                    if (space != null) {
                        _parkingSpaces.value?.add(space)
                    }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "exception:", exception)
            }
    }


}