package com.example.chatapplication.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.chatapplication.R
import com.example.chatapplication.databinding.FragmentAuthOtpVerificationBinding



class AuthOtpVerificationFragment : Fragment(R.layout.fragment_auth_otp_verification) {
    private lateinit var binding: FragmentAuthOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentAuthOtpVerificationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.etPhoneNo.setText(arguments?.getString("MobileNo"))
//        binding.signUpBtn.setOnClickListener {
//
//
//            val intent = Intent(context, MainActivity::class.java)
//            // Clear the back stack
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            startActivity(intent)
//
//        }
//        binding.icBack.setOnClickListener {
//            navigation(R.id.signUpFragment, R.id.signFragment)
//        }
//        binding.haveSignIn.setOnClickListener {
//            navigation(R.id.signUpFragment, R.id.signFragment)
//        }
    }

    private fun navigation(from: Int, to: Int) {
        val navController = findNavController()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(
                from,
                true
            ) // Replace with the actual ID of the current fragment
            .build()
        navController.navigate(to, null, navOptions)
    }

}