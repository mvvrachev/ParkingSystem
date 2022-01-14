package com.example.parkingsystem.data.datasources

import android.os.Handler
import android.os.Looper
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result

class MockDataSource {

    // TODO: check what info should be returned for user
    fun doLogin(email: String, password: String, repositoryResult: RepositoryResult) {
        delayResponse() {
            repositoryResult.result(Result.Success(Unit))
        }
    }


    private fun delayResponse(delayedFuncExecution: () -> Unit) {
        Handler(Looper.myLooper()!!).postDelayed({
            delayedFuncExecution()
        }, 2000)
    }
}