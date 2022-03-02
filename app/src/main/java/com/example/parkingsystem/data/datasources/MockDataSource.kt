package com.example.parkingsystem.data.datasources

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.models.ParkingSpace
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MockDataSource {

    // TODO: check what info should be returned for user
    fun doLogin(email: String, password: String, repositoryResult: RepositoryResult) {
        delayResponse() {
            // TODO: add valid email validation
            if (email.isEmpty() || password.isEmpty()) {
                repositoryResult.result(Result.Error("Fields must not be empty!"))
                return@delayResponse
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                repositoryResult.result(Result.Error("Invalid email address!"))
                return@delayResponse
            }

            repositoryResult.result(Result.Success(Unit))
        }
    }

    fun doRegister(username: String, email: String, carNumber: String, password: String, repositoryResult: RepositoryResult) {
        delayResponse {
            return@delayResponse
        }

        repositoryResult.result(Result.Success(Unit))
    }

    private fun delayResponse(delayedFuncExecution: () -> Unit) {
        Handler(Looper.myLooper()!!).postDelayed({
            delayedFuncExecution()
        }, 2000)
    }
}