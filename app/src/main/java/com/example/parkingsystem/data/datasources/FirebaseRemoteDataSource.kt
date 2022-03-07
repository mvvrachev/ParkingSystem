package com.example.parkingsystem.data.datasources

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.models.ParkingSpace
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.example.parkingsystem.base.Result

class FirebaseRemoteDataSource {

    fun loadParkingSpaces(repositoryResult: RepositoryResult<List<ParkingSpace>>) {
        val parkingSpaces = mutableListOf<ParkingSpace>()
        val db = Firebase.firestore.collection("parking-management-system").document("parking-spaces")

        // cast firebase obj to local model
        // TODO; Map Firebase complex object to much more simple one
        db.get()
            .addOnSuccessListener { d ->
                val space = d.toObject<ParkingSpace>()
                if (space != null) {
                    parkingSpaces.add(space)
                }


//                repositoryResult.result(Result.Success(Unit))
            }
            .addOnFailureListener { exception ->
                repositoryResult.result(Result.Error(exception.toString()))
            }
    }

}