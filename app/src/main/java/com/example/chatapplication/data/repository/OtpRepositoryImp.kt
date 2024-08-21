package com.example.chatapplication.data.repository

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class OtpRepositoryImp(private val auth: FirebaseAuth) : OTPAuthRepository {

    private val _authStatus = MutableLiveData<String>()
    override val authStatus: LiveData<String> get() = _authStatus

    private val _verificationId = MutableLiveData<String>()
    override val verificationId: LiveData<String> get() = _verificationId

    override fun sendOtp(phoneNumber: String, activity: androidx.fragment.app.FragmentActivity) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(firebaseException: FirebaseException) {

                    TODO("Not yet implemented")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    _verificationId.value = verificationId

                }
            })
            .build()

            PhoneAuthProvider.verifyPhoneNumber(options)

    }

    override fun verifyOtp(otp: String) {
        _verificationId.value.let {
            if(it!=null){
                val credential = PhoneAuthProvider.getCredential(it,otp)
                signInWithPhoneAuthCredential(credential)
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    _authStatus.postValue("Successfully verified")
                }
            }
    }


}