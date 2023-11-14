package com.example.loginexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.loginexercise.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreferences = UserPreferences(this)

        //region set up
        setupRememberSwitch()
        setUpClickListeners()
        setUpUI()
        //endregion
    }

    //region set up methods
    private fun setUpClickListeners(){
        binding.loginBtnClose.setOnClickListener { finish() }
        binding.loginBtnLogin.setOnClickListener { submitForm() }
    }

    private fun setupRememberSwitch() {
        val rememberSwitch = binding.loginSwitchRemember
        rememberSwitch.isChecked = userPreferences.isRememberPassword()
    }

    private fun setUpLoginButton() {
        val savedEmail by lazy { intent.getStringExtra("email") }
        val savedPassword by lazy { intent.getStringExtra("password") }

        if (savedEmail != null && savedPassword != null) {
            val loginButton = binding.loginBtnLogin
            binding.loginContainerPassword.helperText = null
            binding.loginContainerEmail.helperText = null

            loginButton.isEnabled = binding.loginContainerPassword.helperText == null && binding.loginContainerEmail.helperText == null
            binding.loginInputEmail.setText(savedEmail)
            binding.loginInputPassword.setText(savedPassword)
        } else {
            //do nothing
        }
    }
    private fun setUpInputEmail() {
        binding.loginInputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                validateForm()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }
    private fun setUpInputPassword() {
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
    private fun setUpUI() {
        setUpLoginButton()
        setUpInputEmail()
        setUpInputPassword()
    }
    //endregion

    //region submit data methods
    private fun submitForm() {
        val validEmail = binding.loginContainerEmail.helperText == null
        val validPassword = binding.loginContainerPassword.helperText == null

        if (validEmail && validPassword) {
            binding.loginBtnLogin.visibility = View.GONE
            binding.loginProgressIndicatorLoading.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                val email = binding.loginInputEmail.text.toString()
                val password = binding.loginInputPassword.text.toString()
                val user = UserProvider.mockUsers.find { it.email == email && it.password == password }

                if (user != null) {
                    submitData()
                } else {
                    binding.loginBtnLogin.visibility = View.VISIBLE
                    binding.loginProgressIndicatorLoading.visibility = View.GONE

                    invalidForm()
                }
            }, 1000)
        }
    }

    private fun createWelcomeActivity() {
        val savedEmail = binding.loginInputEmail.text.toString()
        val savedPassword = binding.loginInputPassword.text.toString()
        val welcomeIntent = Intent(this, WelcomeActivity::class.java)

        userPreferences.saveRememberedEmail(savedEmail)
        userPreferences.saveRememberedPassword(savedPassword)

        welcomeIntent.putExtra("email", savedEmail)
        welcomeIntent.putExtra("password", savedPassword)
        startActivity(welcomeIntent)
    }

    private fun submitData() {
        val rememberSwitch = binding.loginSwitchRemember
        val savedEmail = binding.loginInputEmail.text.toString()
        val welcomeIntent = Intent(this, WelcomeActivity::class.java)

        if (rememberSwitch.isChecked) {
            userPreferences.setRememberPassword(rememberSwitch.isChecked)
            createWelcomeActivity()
            finish()
        } else {
            welcomeIntent.putExtra("email", savedEmail)
            startActivity(welcomeIntent)
            finish()
        }
    }
    //endregion

    //region validation methods
    private fun invalidForm() {
        AlertDialog.Builder(this)
            .setTitle(R.string.errorDialogConectTitle)
            .setMessage(R.string.errorMsgUserNotFound)
            .setPositiveButton(R.string.errorDialogAccept) { _, _ -> }.show()
    }

    private fun validEmail(): String? {
        val emailText = binding.loginInputEmail.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.errorEmailValid)
        }
        return null
    }

    private fun validPassword(): String? {
        val passwordText = binding.loginInputPassword.text.toString()

        if (passwordText.length < 6) {
            return getString(R.string.errorMsgLength)
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return getString(R.string.errorMsgUpperCase)
        }
        if (!passwordText.matches(".*[a-z].*".toRegex()))
            return getString(R.string.errorMsgLowerCase)
        if (!passwordText.matches(".*[0-9].*".toRegex())) {
            return getString(R.string.errorMsgNumber)
        }
        return null
    }

    private fun validateForm() {
        val loginButton = binding.loginBtnLogin
        binding.loginContainerPassword.helperText = validPassword()
        binding.loginContainerEmail.helperText = validEmail()

        loginButton.isEnabled = validPassword() == null && validEmail() == null
    }
    //endregion

}