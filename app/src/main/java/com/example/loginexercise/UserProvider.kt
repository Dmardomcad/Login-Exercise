package com.example.loginexercise

class UserProvider {
    companion object{
        val mockUsers = listOf<User>(
            User(
                "Pepe",
                "pepegzlez@example.es",
                "a123456B"
            ),
            User(
                "shadow",
                "edgyemail@example.es",
                "B65432a"
            )
        )
    }
}