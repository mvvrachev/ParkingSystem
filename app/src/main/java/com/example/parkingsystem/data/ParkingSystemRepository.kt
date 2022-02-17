package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult

interface ParkingSystemRepository {

    fun doLogin(email: String, password: String, callback: RepositoryResult)
    fun doRegister(username: String, email: String, carNumber: String, password: String, callback: RepositoryResult)

    fun loadParkingSpaces(callback: RepositoryResult)

}