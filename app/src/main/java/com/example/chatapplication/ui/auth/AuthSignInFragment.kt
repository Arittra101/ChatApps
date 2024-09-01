package com.example.chatapplication.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavOptions
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Toast
import com.example.chatapplication.R
import com.example.chatapplication.databinding.FragmentAuthSignInBinding
import com.example.chatapplication.utility.Navigation
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class AuthSignInFragment : Fragment(R.layout.fragment_auth_sign_in) {
    private lateinit var binding: FragmentAuthSignInBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentAuthSignInBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.sendOtp.setOnClickListener {

            val phoneNo = binding.etPhoneNo.text.toString()
            val bundle = Bundle().apply {
                putString("MobileNo",phoneNo)
            }
            Navigation.navigate(this,R.id.AuthSignFragment,R.id.AuthOtpVerificationFragment,bundle)

        }
    }

}