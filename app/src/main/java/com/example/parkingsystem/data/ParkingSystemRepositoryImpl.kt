package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.data.datasources.FirebaseRemoteDataSource
import com.example.parkingsystem.data.datasources.MockDataSource
import com.example.parkingsystem.models.ParkingSpace
import com.example.parkingsystem.models.User

class ParkingSystemRepositoryImpl(
    private val mockDataSource: MockDataSource = MockDataSource(),
    private val firebaseRemoteDataSource: FirebaseRemoteDataSource = FirebaseRemoteDataSource()
) : ParkingSystemRepository {

    override fun doRegister(
        username: String,
        email: String,
        carNumber: String,
        password: String,
        confirmPassword: String,
        callback: RepositoryResult<Unit>
    ) {
        firebaseRemoteDataSource.doRegister(username, email, carNumber, password, confirmPassword, callback)
    }

    override fun doLogin(email: String, password: String, callback: RepositoryResult<Unit>) {
        firebaseRemoteDataSource.doLogin(email, password, callback)
    }

    override fun doLogout(callback: RepositoryResult<Unit>) {
        firebaseRemoteDataSource.doLogout(callback)
    }

    override fun loadParkingSpaces(callback: RepositoryResult<List<ParkingSpace>>) {
        firebaseRemoteDataSource.loadParkingSpaces(callback)
    }

    override fun makeReservation(id: Long, date: String, callback: RepositoryResult<Unit>) {
        firebaseRemoteDataSource.makeReservation(id, date, callback)
    }

    override fun fetchUserInfo(callback: RepositoryResult<User>) {
        firebaseRemoteDataSource.fetchUserInfo(callback)
    }
}