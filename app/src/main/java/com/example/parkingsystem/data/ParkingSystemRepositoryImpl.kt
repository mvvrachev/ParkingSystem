package com.example.parkingsystem.data

import com.example.parkingsystem.base.RepositoryResult
import com.example.parkingsystem.data.datasources.MockDataSource

class ParkingSystemRepositoryImpl(
    private val mockDataSource: MockDataSource = MockDataSource()
) : ParkingSystemRepository {

    override fun doLogin(email: String, password: String, callback: RepositoryResult) {
        // TODO: validation to be done here
        mockDataSource.doLogin(email, password, callback)
    }
}