package com.example.parkingsystem.models

import java.lang.NumberFormatException

data class Reservation (
    val carNumber: String? = null,
    val date: String? = null,
    val space: Long? = null,
    val user_id: String? = null
)