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

        getUserData()
        //region Init bindings
        binding.welcomeBtnCloseSession.setOnClickListener { closeSession() }
        //endregion
    }

    private fun closeSession(){
        val savedEmail = intent.getStringExtra("email")
        val savedPassword = intent.getStringExtra("password")
        val loginIntent = Intent(this, LoginActivity::class.java)
        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setMessage(R.string.confirm_close_session)
        alertDialogBuilder.setPositiveButton(R.string.confirm_close_session_positive){
            _, _ ->
            loginIntent.putExtra("email", savedEmail)
            loginIntent.putExtra("password",savedPassword)
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

    private fun getUserData(){
        val savedEmail = intent.getStringExtra("email")
        val user = UserProvider.mockUsers.find { it.email == savedEmail }

        if (user != null) {
            binding.homeLabelThisUserEmail.text = user.email
            binding.homeLabelThisUserName.text = user.username
            binding.homeImgAvatar.setImageResource(user.avatarId)
        }
    }

}
