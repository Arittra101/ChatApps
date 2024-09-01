package com.example.chatapplication.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.chatapplication.data.repository.OTPAuthRepository
import com.example.chatapplication.data.repository.OtpRepositoryImp

class AuthViewModel(private val authreop: OTPAuthRepository) :ViewModel() {
    val authStatus: LiveData<String> = authreop.authStatus
    val codeSentStatus : LiveData<String> = authreop.codeSentStatus

    fun sentOtp(phoneNumber: String,activity: androidx.fragment.app.FragmentActivity,resendOtp:Boolean) {
        val reformPhoneNo = phoneNumber
        authreop.sendOtp(reformPhoneNo,activity,resendOtp)
    }
    fun verifyOtp(otp: String) {
        authreop.verifyOtp(otp)
    }


}