package com.example.parkingsystem.data.datasources

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.models.ParkingSpace
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.example.parkingsystem.base.Result
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth

class FirebaseRemoteDataSource {

    private val auth = Firebase.auth

    fun doRegister(username: String, email: String, carNumber: String, password: String, confirmPassword: String, repositoryResult: RepositoryResult<Unit>) {
        if(username.isEmpty() || email.isEmpty() || carNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            repositoryResult.result(Result.Error("Fields must not be empty!"))
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            repositoryResult.result(Result.Error("Invalid email address!"))
        }
        else if(email.split("@")[1] != "elsys-bg.org") {
            repositoryResult.result(Result.Error("Not an ELSYS email address!"))
        }
        else if(password.length < 8) {
            repositoryResult.result(Result.Error("Password must be at least 8 characters!"))
        }
        else if(password != confirmPassword) {
            repositoryResult.result(Result.Error("Passwords do not match!"))
        }
        else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        addAdditionalUserInfo(username, carNumber, auth.currentUser!!)
                        repositoryResult.result(Result.Success(Unit))
                    } else {
                        repositoryResult.result(Result.Error(task.exception.toString()))
                    }
                }
        }
    }

    private fun addAdditionalUserInfo(username: String, carNumber: String, user: FirebaseUser) {
        val db = Firebase.firestore
        val values = hashMapOf("username" to username, "carNumber" to carNumber)
        db.collection("user-profiles").document(user.uid).set(values)
    }

    fun doLogin(email: String, password: String, repositoryResult: RepositoryResult<Unit>) {
        if(email.isEmpty() || password.isEmpty()) {
            repositoryResult.result(Result.Error("Please fill in the fields!"))
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        repositoryResult.result(Result.Success(Unit))
                    } else {
                        repositoryResult.result(Result.Error(task.exception.toString()))
                    }
                }
        }
    }

    fun loadParkingSpaces(repositoryResult: RepositoryResult<List<ParkingSpace>>) {
        val parkingSpaces = mutableListOf<ParkingSpace>()
        val db = Firebase.firestore.collection("parking-spaces")

        db.get()
            .addOnSuccessListener { documents ->
                for(document in documents) {
                    parkingSpaces.add(document.toObject())
                }
                repositoryResult.result(Result.Success(parkingSpaces))
            }
            .addOnFailureListener { exception ->
                repositoryResult.result(Result.Error(exception.toString()))
            }
    }
}