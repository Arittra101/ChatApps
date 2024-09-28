package com.example.chatapplication.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.chatapplication.R
import com.example.chatapplication.databinding.FragmentAuthSignInBinding
import com.example.chatapplication.utility.Navigation


class AuthSignInFragment : Fragment(R.layout.fragment_auth_sign_in) {
    private lateinit var binding: FragmentAuthSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentAuthSignInBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.sendOtp.setOnClickListener {

            val phoneNo = binding.etPhoneNo.text.toString()
            val formattedPhoneNo =  formatPhoneNumber(phoneNo)

            if (formattedPhoneNo.startsWith("+")) {
                val bundle = Bundle().apply {
                    putString("MobileNo", formattedPhoneNo)
                }
                Navigation.navigate(
                    this,
                    R.id.AuthSignFragment,
                    R.id.AuthOtpVerificationFragment,
                    bundle
                )
            }else{
                Toast.makeText(requireContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun formatPhoneNumber(phoneNo: String):String {

        return if(phoneNo.startsWith("+") || phoneNo.startsWith("+880")){
            phoneNo
        }else  {
            "+880"+phoneNo.substring(1)
        }

    }

}