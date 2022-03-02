package com.example.parkingsystem.data.datasources

import android.content.ContentValues.TAG
import android.util.Log
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.models.ParkingSpace
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class FirebaseRemoteDataSource {

    fun loadParkingSpaces(repositoryResult: RepositoryResult) {
        val parkingSpaces = mutableListOf<ParkingSpace>()
        val db = Firebase.firestore.collection("parking-management-system").document("parking-spaces")
        db.get()
            .addOnSuccessListener { d ->
                val space = d.toObject<ParkingSpace>()
                if (space != null) {
                    parkingSpaces.add(space)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }




    }

}