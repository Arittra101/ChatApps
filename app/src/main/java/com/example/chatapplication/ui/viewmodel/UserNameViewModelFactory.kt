package com.example.chatapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.data.repository.auth.OTPAuthRepository
import com.example.chatapplication.data.repository.userinfo.UserInfoRepository
import com.example.chatapplication.ui.auth.AuthViewModel
import com.example.chatapplication.ui.home.UserNameViewModel

class UserNameViewModelFactory(private val userInfoRep: UserInfoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserNameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserNameViewModel(userInfoRep) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}