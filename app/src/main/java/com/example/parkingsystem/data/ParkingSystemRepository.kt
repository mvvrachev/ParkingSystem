package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.models.ParkingSpace

interface ParkingSystemRepository {

    fun doLogin(email: String, password: String, callback: RepositoryResult)
    fun doRegister(username: String, email: String, carNumber: String, password: String, callback: RepositoryResult)

    fun loadParkingSpaces(callback: RepositoryResult): MutableList<ParkingSpace>

}