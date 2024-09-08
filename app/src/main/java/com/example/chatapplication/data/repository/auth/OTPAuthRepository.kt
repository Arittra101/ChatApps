package com.example.chatapplication.data.repository.auth

import androidx.lifecycle.LiveData

interface OTPAuthRepository {
    val authStatus: LiveData<String>
    val verificationId: LiveData<String>?
    val codeSentStatus: LiveData<String>

    fun sendOtp(phoneNumber: String,activity: androidx.fragment.app.FragmentActivity,resend:Boolean)
    fun verifyOtp(otp: String)
}