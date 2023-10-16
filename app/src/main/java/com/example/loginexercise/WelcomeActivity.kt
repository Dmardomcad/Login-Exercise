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

        //region Init bindings
        binding.welcomeBtnCloseSession.setOnClickListener { closeSession() }
        getExtras()
        //endregion
    }

    private fun getExtras(){
        //region text view bindings
        val savedEmail = intent.getStringExtra("email")
        binding.homeLabelThisUserEmail.text = savedEmail

        val savedName = intent.getStringExtra("name")
        binding.homeLabelThisUserName.text = savedName
        //endregion

        val savedImage = intent.getIntExtra("avatar", 0)
        if(savedImage != 0){
            binding.homeImgAvatar.setImageResource(savedImage)
        }
    }

    private fun closeSession(){
        val savedEmail = intent.getStringExtra("rememberEmail")
        val savedPassword = intent.getStringExtra("rememberPassword")

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage(R.string.confirm_close_session)
        alertDialogBuilder.setPositiveButton(R.string.confirm_close_session_positive){
            _, _ ->
            val loginIntent = Intent(this, LoginActivity::class.java)
            loginIntent.putExtra("rememberEmail", savedEmail)
            loginIntent.putExtra("rememberPassword",savedPassword)
            startActivity(loginIntent)
            finish()
        }
        alertDialogBuilder.setNegativeButton(R.string.confirm_close_session_negative) {
            dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        }

    }
