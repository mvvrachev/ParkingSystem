package com.example.parkingsystem.data.datasources

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result


class MockDataSource {

    // TODO: check what info should be returned for user
    fun doLogin(email: String, password: String, repositoryResult: RepositoryResult<Unit>) {
        delayResponse() {
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

//    fun doRegister(username: String, email: String, carNumber: String, password: String, confirmPassword: String, repositoryResult: RepositoryResult<Unit>) {
//        delayResponse() {
//            if(username.isEmpty() || email.isEmpty() || carNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//                repositoryResult.result(Result.Error("Fields must not be empty!"))
//                return@delayResponse
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                repositoryResult.result(Result.Error("Invalid email address!"))
//                return@delayResponse
//            }
//            if(password.length < 8) {
//                repositoryResult.result(Result.Error("Password must be at least 8 characters!"))
//                return@delayResponse
//            }
//            if(password != confirmPassword) {
//                repositoryResult.result(Result.Error("Passwords do not match!"))
//                return@delayResponse
//            }
//
//            repositoryResult.result(Result.Success(Unit))
//        }
//    }

    private fun delayResponse(delayedFuncExecution: () -> Unit) {
        Handler(Looper.myLooper()!!).postDelayed({
            delayedFuncExecution()
        }, 2000)
    }
}