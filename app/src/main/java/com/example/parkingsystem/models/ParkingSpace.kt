package com.example.parkingsystem.models

data class ParkingSpace(
    val spaces: List<HashMap<String, Long>>? = null
)

data class Parking(
    val name: String
)