package com.example.chatapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.data.repository.userinfo.UserInfoRepository
import com.example.chatapplication.ui.username.UserNameSetUpViewModel

class UserNameViewModelFactory(private val userInfoRep: UserInfoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserNameSetUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserNameSetUpViewModel(userInfoRep) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}