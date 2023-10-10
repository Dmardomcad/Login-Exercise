package com.example.loginexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
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

        binding.loginBtnLogin.setOnClickListener { submitForm() }


        binding.loginInputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                validateForm()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        binding.loginInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                validateForm()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    private fun validateForm(){
        val loginButton = binding.loginBtnLogin
        if(validPassword() == null && validEmail() == null){
            loginButton.isEnabled = true
        } else {
            loginButton.isEnabled = false
        }
    }



    private fun closeApp(){
        finish()
    }

    private fun submitForm(){
        val validEmail = binding.loginContainerEmail.helperText == null
        val validPassword = binding.loginContainerPassword.helperText == null

        if(validEmail && validPassword){
            submitData()
        }
        else{
            invalidForm()
        }
    }

    private fun submitData(){
        //TO DO
    }
    private fun invalidForm(){
        var message = ""
        if(binding.loginContainerEmail.helperText != null){
            message += "\n\nEmail: " + binding.loginContainerEmail.helperText
        }
        if(binding.loginContainerPassword.helperText != null){
            message += "\n\nPassword: " + binding.loginContainerPassword.helperText
        }

        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Okey"){_,_ -> }.show()
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
        if(!passwordText.matches(".*[a-z].*".toRegex()))
            return "Debe tener al menos 1 minúscula"
        if(!passwordText.matches(".*[0-9].*".toRegex())){
            return "Debe tener al menos 1 número"
        }
        return null
    }

}