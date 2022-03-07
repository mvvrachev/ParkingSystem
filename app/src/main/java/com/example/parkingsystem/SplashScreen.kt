package com.example.parkingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        //TODO: Check whether user is logged in
        // https://bignerdranch.com/blog/splash-screens-the-right-way/

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}