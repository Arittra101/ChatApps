package com.example.chatapplication.data.repository

import androidx.lifecycle.LiveData

interface OTPAuthRepository {
    val authStatus: LiveData<String>
    val verificationId: LiveData<String>?

    fun sendOtp(phoneNumber: String,activity: androidx.fragment.app.FragmentActivity)
    fun verifyOtp(otp: String)
}