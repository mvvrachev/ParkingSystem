package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.data.datasources.FirebaseRemoteDataSource
import com.example.parkingsystem.data.datasources.MockDataSource

class ParkingSystemRepositoryImpl(
    private val mockDataSource: MockDataSource = MockDataSource(),
    private val firebaseRemoteDataSource: FirebaseRemoteDataSource = FirebaseRemoteDataSource()
) : ParkingSystemRepository {

    override fun doLogin(email: String, password: String, callback: RepositoryResult) {
        // TODO: validation to be done here
        mockDataSource.doLogin(email, password, callback)
    }

    override fun doRegister(
        username: String,
        email: String,
        carNumber: String,
        password: String,
        callback: RepositoryResult
    ) {
        mockDataSource.doRegister(username, email, carNumber, password, callback)
    }

    override fun loadParkingSpaces(callback: RepositoryResult) {
        firebaseRemoteDataSource.loadParkingSpaces(callback)
    }
}