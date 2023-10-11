package com.example.loginexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.loginexercise.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeBtnCloseSession.setOnClickListener { closeSession() }

        val savedEmail = intent.getStringExtra("email")
        binding.homeLabelThisUserEmail.text = savedEmail

        }

    private fun closeSession(){
        val savedEmail = intent.getStringExtra("email")
        val savedPassword = intent.getStringExtra("password")
        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setMessage("¿Cerrar sesión?")
        alertDialogBuilder.setPositiveButton("Sí"){
            _, _ ->
            val loginIntent = Intent(this, LoginActivity::class.java)
            loginIntent.putExtra("email", savedEmail)
            loginIntent.putExtra("password",savedPassword)
            startActivity(loginIntent)
            finish()
        }
        alertDialogBuilder.setNegativeButton("No") {
            dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        }

    }
