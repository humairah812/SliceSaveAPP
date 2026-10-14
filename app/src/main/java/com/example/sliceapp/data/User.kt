package com.example.sliceapp.data

data class User(val fullName: String, val email: String, val password: String)

val users = mutableListOf<User>()