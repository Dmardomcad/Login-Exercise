package com.example.loginexercise

class UserProvider {
    companion object{
        val mockUsers = listOf<User>(
            User(
                "Pepe",
                "pepegzlez@example.es",
                "a123456B",
                R.drawable.img__home_screen_pepe
            ),
            User(
                "shadow",
                "edgyemail@example.es",
                "B65432a",
                R.drawable.img__home_screen_shadow
            )
        )
    }
}