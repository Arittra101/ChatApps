package com.example.chatapplication.data.repository.auth

import android.util.Log
import android.widget.Toast
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
    override val codeSentStatus: LiveData<String> get() = _codeSentStatus


    private val _verificationId = MutableLiveData<String>()
    private val _codeSentStatus = MutableLiveData<String>()
    private lateinit var  resendToken : PhoneAuthProvider.ForceResendingToken
    override val verificationId: LiveData<String> get() = _verificationId

    override fun sendOtp(phoneNumber: String, activity: FragmentActivity,resend:Boolean) {

        val optionsBuilder  = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(firebaseException: FirebaseException) {

                    Toast.makeText(activity,"$firebaseException",Toast.LENGTH_SHORT).show()
                    Log.wtf("OTP","$firebaseException")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    _verificationId.postValue(verificationId)
                    _codeSentStatus.postValue("CodeSent")
                    resendToken =  token

                }
            })


        if(resend && ::resendToken.isInitialized)  {
            optionsBuilder.setForceResendingToken(resendToken)
        }
        val options = optionsBuilder.build()
        PhoneAuthProvider.verifyPhoneNumber(options)
0


    }
    override fun verifyOtp(otp: String) {
        _verificationId.value.let {
            if(it!=null){
                val credential = PhoneAuthProvider.getCredential(it,otp)
                signInWithPhoneAuthCredential(credential)
            }
        }
    }

    //we can write final verification business logic here
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                Log.wtf("got","success")
                    _authStatus.postValue("SuccessfullyVerified")
                }else{
                    Log.wtf("got1","failed")
                }
            }
    }


}