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

            val navController = findNavController()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(
                    R.id.AuthSignFragment,
                    true
                )
                .build()
            val phoneNo = binding.etPhoneNo.text.toString()
            val bundle = Bundle().apply {
                putString("MobileNo",phoneNo)
            }
            navController.navigate(R.id.AuthOtpVerificationFragment, bundle, navOptions)
        }
    }

}