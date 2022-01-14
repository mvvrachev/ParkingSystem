package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult

interface ParkingSystemRepository {

    fun doLogin(email: String, password: String, callback: RepositoryResult)

}