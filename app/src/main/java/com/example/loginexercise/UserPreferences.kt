package com.example.loginexercise

import android.content.Context

class UserPreferences (context: Context){
    private val sharedPreferences = context.applicationContext.getSharedPreferences("login_data", Context.MODE_PRIVATE)

    fun isRememberPassword(): Boolean{
        return sharedPreferences.getBoolean("remember_password", false)
    }
    fun setRememberPassword(remember: Boolean){
        sharedPreferences.edit().putBoolean("remember_password",remember).apply()
    }
    fun saveRememberedEmail(email: String){
        sharedPreferences.edit().putString("remembered_email", email).apply()
    }
    fun getRememberedEmail(): String?{
        return sharedPreferences.getString("remembered_email", null)
    }
    fun saveRememberedPassword(password: String){
        sharedPreferences.edit().putString("remembered_password", password).apply()
    }
    fun getRememberedPassword(): String? {
        return sharedPreferences.getString("remembered_password",null)
    }
}