package com.example.parkingsystem.data.datasources

import android.os.Handler
import android.os.Looper
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.models.ParkingSpace

class MockDataSource {

    // TODO: check what info should be returned for user
    fun doLogin(email: String, password: String, repositoryResult: RepositoryResult) {
        delayResponse() {
            // TODO: add valid email validation
            if (email.isEmpty() || password.isEmpty()) {
                repositoryResult.result(Result.Error("All fields must not be empty"))
                return@delayResponse
            }

            repositoryResult.result(Result.Success(Unit))
        }
    }

    fun doRegister(username: String, email: String, carNumber: String, password: String, repositoryResult: RepositoryResult) {
        delayResponse {
            return@delayResponse
        }
    }

    fun loadParkingSpaces(repositoryResult: RepositoryResult) {
        val parkingSpaces = listOf(ParkingSpace("48"))
    }


    private fun delayResponse(delayedFuncExecution: () -> Unit) {
        Handler(Looper.myLooper()!!).postDelayed({
            delayedFuncExecution()
        }, 2000)
    }
}