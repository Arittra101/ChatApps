package com.example.chatapplication.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.R
import com.example.chatapplication.data.repository.auth.OtpRepositoryImp
import com.example.chatapplication.databinding.FragmentAuthOtpVerificationBinding
import com.example.chatapplication.ui.viewmodel.AuthViewModelFactory
import com.example.chatapplication.utility.Navigation
import com.google.firebase.auth.FirebaseAuth


class AuthOtpVerificationFragment : Fragment(R.layout.fragment_auth_otp_verification) {
    private lateinit var binding: FragmentAuthOtpVerificationBinding
    private lateinit var authViewModel: AuthViewModel
    private var phoneNo : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthOtpVerificationBinding.bind(view)
        val authRepository = OtpRepositoryImp(FirebaseAuth.getInstance())

        authViewModel = ViewModelProvider(this, AuthViewModelFactory(authRepository))[AuthViewModel::class.java]


        phoneNo = arguments?.getString("MobileNo")

         phoneNo?.let {
             authViewModel.sentOtp(it, requireActivity(), false)
         }

        setTimerForResendBtn()

        binding.sendOtp.setOnClickListener {
            val otp = binding.etOtp.text.toString()
            if (otp.isNotBlank()) {
                authViewModel.verifyOtp(otp)
                binding.progressBar.visibility = View.VISIBLE
            } else {
                Toast.makeText(requireContext(), "Please enter otp", Toast.LENGTH_SHORT).show()
            }
        }

        initObserver()

        binding.resendBtn.setOnClickListener {
            phoneNo?.let {
                authViewModel.sentOtp(it, requireActivity(), false)
            }

            setTimerForResendBtn()
        }
    }

    private fun initObserver() {
        authViewModel.codeSentStatus.observe(viewLifecycleOwner) { status ->
            if (status.equals("CodeSent")) {
                binding.sendOtp.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }

        authViewModel.authStatus.observe(viewLifecycleOwner) { authStatus ->
            if (authStatus.equals("SuccessfullyVerified")) {

                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "SuccessfullyVerifiedFragmetn", Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
                val bundle = Bundle().apply {
                    putString("MobileNo", phoneNo)
                }

                Navigation.navigate(
                    this,
                    R.id.AuthOtpVerificationFragment,
                    R.id.userNameFragment,
                    bundle
                )

            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setTimerForResendBtn() {
        binding.progressBar.visibility = View.GONE
        binding.resendBtn.isEnabled = false
        object : CountDownTimer(30000, 1000) { // 30 seconds countdown with 1-second intervals
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                binding.resendBtn.text = "You Can Resend OTP after $secondsRemaining seconds"
            }

            override fun onFinish() {
                binding.resendBtn.isEnabled = true // Re-enable the button
                binding.resendBtn.text = "Click Here to Resend" // Reset the button text
            }
        }.start()
    }

}