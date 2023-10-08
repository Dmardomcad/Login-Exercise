package com.example.loginexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.loginexercise.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtnClose.setOnClickListener{ closeApp() }
        emailFocusListener()
        passwordFocusListener()
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

    private fun emailFocusListener(){
        binding.loginInputEmail.setOnFocusChangeListener {_, focused ->
            if (!focused){
                binding.loginContainerEmail.helperText = validEmail()
            }
        }
    }
    private fun validEmail(): String?{
        val emailText = binding.loginInputEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Email no válido"
        }

        return null
    }

    private fun passwordFocusListener(){
        binding.loginInputPassword.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.loginContainerPassword.helperText = validPassword()
            }
        }
    }
    private fun validPassword(): String?{
        val passwordText = binding.loginInputPassword.text.toString()
        if(passwordText.length < 6){
            return "Mínimo 6 carácteres"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex())){
            return "Debe tener al menos 1 mayúscula"
        }
        //To do rest of the validations...

        return null
    }
}