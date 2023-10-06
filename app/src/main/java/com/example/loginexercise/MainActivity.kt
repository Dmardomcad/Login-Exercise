package com.example.loginexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginexercise.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtnClose.setOnClickListener{ closeApp() }
    }
    private fun closeApp(){
        finish()
    }

    data class User(
        val username: String,
        val email: String,
        val password: String,
    )

    val mockUsers = listOf(
        User("Pepe","pepegzlez@example.es","a1234567"),
        User("shadowdarkness09","edgyemail@example.es","darks234")
    )

}