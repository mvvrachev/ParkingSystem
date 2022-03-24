package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.models.FirebaseParkingSpace
import com.example.parkingsystem.models.ParkingSpace

interface ParkingSystemRepository {

    fun doRegister(username: String, email: String, carNumber: String, password: String,confirmPassword: String, callback: RepositoryResult<Unit>)
    fun doLogin(email: String, password: String, callback: RepositoryResult<Unit>)
    fun doLogout(callback: RepositoryResult<Unit>)

    fun loadParkingSpaces(callback: RepositoryResult<List<ParkingSpace>>)
    fun makeReservation(id: Long, date: String, callback: RepositoryResult<Unit>)


}