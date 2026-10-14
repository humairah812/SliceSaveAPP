package com.example.sliceapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.sliceapp.data.User
import com.example.sliceapp.data.users

class AuthViewModel : ViewModel() {

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var fullNameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)

    fun validateSignUp(): Boolean {
        var isValid = true

        if (fullName.isBlank()) {
            fullNameError = "Full name cannot be empty"
            isValid = false
        } else {
            fullNameError = null
        }

        if (email.isBlank()) {
            emailError = "Email cannot be empty"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Enter a valid email"
            isValid = false
        } else {
            emailError = null
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordError = null
        }

        return isValid
    }

    fun validateLogin(): Boolean {
        var isValid = true

        if (email.isBlank()) {
            emailError = "Email cannot be empty"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Enter a valid email"
            isValid = false
        } else {
            emailError = null
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
            isValid = false
        } else {
            passwordError = null
        }

        return isValid
    }
    
    fun signUp(): Boolean {
        if(validateSignUp()){
            if(users.none { it.email == email }){
                users.add(User(fullName, email, password))
                return true
            } else {
                emailError = "User with this email already exists"
                return false
            }
        }
        return false
    }
    
    fun login(): Boolean {
        if(validateLogin()){
            val user = users.find { it.email == email }
            if(user != null) {
                if(user.password == password){
                    return true
                } else {
                    passwordError = "Incorrect password"
                    return false
                }
            } else {
                emailError = "User not found"
                return false
            }
        }
        return false
    }
}