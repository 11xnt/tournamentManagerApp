package com.example.tournamentmanagerapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// sourced from:
// https://proandroiddev.com/splash-screen-in-android-3bd9552b92a5
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashScreenActivity, GoogleSignInActivity::class.java))
        finish()
    }
}