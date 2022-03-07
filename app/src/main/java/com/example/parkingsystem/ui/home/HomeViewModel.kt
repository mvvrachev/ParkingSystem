package com.example.parkingsystem.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.base.Result
import com.example.parkingsystem.base.Result.Error
import com.example.parkingsystem.base.Result.Success
import com.example.parkingsystem.data.ParkingSystemRepository
import com.example.parkingsystem.data.ParkingSystemRepositoryImpl
import com.example.parkingsystem.models.ParkingSpace

class HomeViewModel(private val repository: ParkingSystemRepository = ParkingSystemRepositoryImpl()) : ViewModel() {

    private val _parkingSpaces: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState())
    val parkingSpaces: LiveData<HomeViewState> = _parkingSpaces

    init {
        loadParkingSpaces()
    }

    fun loadParkingSpaces() {
        _parkingSpaces.value = _parkingSpaces.value?.copy(isLoading = true, error = "")
        repository.loadParkingSpaces(object : RepositoryResult<List<ParkingSpace>> {
            override fun result(result: Result<List<ParkingSpace>>) {
                when(result) {
                    is Success -> {
                        _parkingSpaces.value = _parkingSpaces.value?.copy(isLoading = false, data = result.data, error = "")
                    }
                    is Error -> {
                        _parkingSpaces.value = _parkingSpaces.value?.copy(isLoading = false, error = result.error)
                    }
                }
            }
        })
    }

    data class HomeViewState(
        val data: List<ParkingSpace> = emptyList(),
        val isLoading: Boolean = false,
        val error: String = ""
    )
}