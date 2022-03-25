package com.example.parkingsystem.ui.userInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.base.Result.Success
import com.example.parkingsystem.base.Result.Error
import com.example.parkingsystem.data.ParkingSystemRepository
import com.example.parkingsystem.data.ParkingSystemRepositoryImpl
import com.example.parkingsystem.models.User
import com.example.parkingsystem.models.UserInfo

class UserInfoViewModel(private val repository: ParkingSystemRepository = ParkingSystemRepositoryImpl()) : ViewModel() {

    private val _userInfoViewState: MutableLiveData<UserInfoViewState> = MutableLiveData(UserInfoViewState())
    val userInfoViewState: LiveData<UserInfoViewState> = _userInfoViewState

    private val _logoutViewState: MutableLiveData<LogoutViewState> = MutableLiveData(LogoutViewState())
    val logoutViewState: LiveData<LogoutViewState> = _logoutViewState

    fun doLogout() {
        _logoutViewState.value = _logoutViewState.value?.copy(isLoading = true)
        repository.doLogout(object : RepositoryResult<Unit> {
            override fun result(result: Result<Unit>) {
                when (result) {
                    is Success -> {
                        _logoutViewState.value = _logoutViewState.value?.copy(successLogout = true, isLoading = false, error = "")
                    }
                    is Error -> {
                        _logoutViewState.value = _logoutViewState.value?.copy(isLoading = false, error = result.error)
                    }
                }
            }
        })
    }

    fun fetchUserInfo() {
        _userInfoViewState.value = _userInfoViewState.value?.copy(isLoading = true)
        repository.fetchUserInfo(object : RepositoryResult<User> {
            override fun result(result: Result<User>) {
                when(result) {
                    is Success -> {
                        _userInfoViewState.value = _userInfoViewState.value?.copy(userData = result.data, isLoading = false, error = "")
                    }
                    is Error -> {
                        _userInfoViewState.value = userInfoViewState.value?.copy(isLoading = false, error = result.error)
                    }
                }
            }
        })
    }

}

data class UserInfoViewState (
    val userData: User = User(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class LogoutViewState (
    val successLogout: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = ""
)
