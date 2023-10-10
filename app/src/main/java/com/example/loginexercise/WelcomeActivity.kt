package com.example.loginexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginexercise.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeBtnCloseSession.setOnClickListener { closeSession() }

        }

    private fun closeSession(){
        val savedEmail = intent.getStringExtra("email")
        val savedPassword = intent.getStringExtra("password")

        val loginIntent = Intent(this, MainActivity::class.java)
        loginIntent.putExtra("email", savedEmail)
        loginIntent.putExtra("password",savedPassword)
        startActivity(loginIntent)
        }

    }
