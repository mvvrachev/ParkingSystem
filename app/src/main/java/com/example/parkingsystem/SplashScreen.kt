package com.example.parkingsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: Check whether user is logged in
        // https://bignerdranch.com/blog/splash-screens-the-right-way/

        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}