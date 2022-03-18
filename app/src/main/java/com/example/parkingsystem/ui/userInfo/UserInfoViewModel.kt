package com.example.parkingsystem.ui.userInfo

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.base.Result.Success
import com.example.parkingsystem.base.Result.Error
import com.example.parkingsystem.data.ParkingSystemRepository
import com.example.parkingsystem.data.ParkingSystemRepositoryImpl
import com.example.parkingsystem.models.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserInfoViewModel(private val repository: ParkingSystemRepository = ParkingSystemRepositoryImpl()) : ViewModel() {

    private val _viewState: MutableLiveData<UserInfoViewState> = MutableLiveData(UserInfoViewState())
    val viewState: LiveData<UserInfoViewState> = _viewState

    fun doLogout() {
        _viewState.value = _viewState.value?.copy(isLoading = true)
        repository.doLogout(object : RepositoryResult<Unit> {
            override fun result(result: Result<Unit>) {
                when (result) {
                    is Success -> {
                        _viewState.value = _viewState.value?.copy(successLogout = true, isLoading = false)
                    }
                    is Error -> {
                        _viewState.value = _viewState.value?.copy(isLoading = false, error = result.error)
                    }
                }
            }
        })
    }

}

data class UserInfoViewState (
    val successLogout: Boolean = false,
    val error: String = "",
    val isLoading: Boolean = false
)
