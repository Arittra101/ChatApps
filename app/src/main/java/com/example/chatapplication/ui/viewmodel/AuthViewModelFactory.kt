package com.example.chatapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.data.repository.OTPAuthRepository
import com.example.chatapplication.data.repository.OtpRepositoryImp
import com.example.chatapplication.ui.auth.AuthViewModel

class AuthViewModelFactory(private val authreop: OTPAuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(authreop) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}