package com.example.savemeal.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.savemeal.R
import com.example.savemeal.databinding.ActivityLoginBinding
import com.example.savemeal.firebase.MyPreferences

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //display token for notificactions
        logFirebaseToken()
    }

    private fun logFirebaseToken() {
        val firebaseTokenText = MyPreferences.getFirebaseToken(this)
        if (firebaseTokenText != null) {
            Log.i("My Firebase Token", firebaseTokenText)
        }
    }
}