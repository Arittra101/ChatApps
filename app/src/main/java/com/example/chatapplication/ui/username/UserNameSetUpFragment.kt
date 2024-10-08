package com.example.chatapplication.ui.username

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.R
import com.example.chatapplication.data.model.UserInfo
import com.example.chatapplication.data.repository.userinfo.UserInfoRepositoryImp
import com.example.chatapplication.databinding.FragmentUserNameBinding
import com.example.chatapplication.ui.viewmodelfactory.UserNameViewModelFactory
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference


class UserNameSetUpFragment : Fragment(R.layout.fragment_user_name) {

    private lateinit var binding: FragmentUserNameBinding
    lateinit private var currentDocumentRef: DocumentReference
    lateinit private var phoneNo: String
    lateinit private var currentUserId: String

    private var currentUserDetails: UserInfo? = null

    private lateinit var userNameViewModel: UserNameSetUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentUserNameBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        phoneNo = arguments?.getString("MobileNo").toString()

        val userInfoRep = UserInfoRepositoryImp()
        userNameViewModel = ViewModelProvider(
            this,
            UserNameViewModelFactory(userInfoRep))[UserNameSetUpViewModel::class.java]

        initialize()

        binding.sendOtp.setOnClickListener {
            isUserNameAvailable(currentUserDetails)
        }
    }

    private fun initialize() {
        userNameViewModel.getCurrentUserId()
        initializeObserver()
    }

    private fun initializeObserver() {

        userNameViewModel.currentUserId.observe(viewLifecycleOwner) {
            currentUserId = it
            Log.wtf("Hellonop", it.toString())
            setInProgress(true)
            userNameViewModel.getCurrentUserDocumentReference(it)
        }

        userNameViewModel.currentUserDocumentReference.observe(viewLifecycleOwner) {
            Log.wtf("currentDetails", it.toString())
            userNameViewModel.getCurrentUserDetails(it)
            currentDocumentRef = it
        }

        userNameViewModel.currentUserDetails.observe(viewLifecycleOwner) {
            currentUserDetails = it
            Log.wtf("currentDetailsOmg", currentUserDetails.toString())

            currentUserDetails?.userName.let {userName->
                binding.userName.setText(userName)
            }
            setInProgress(false)
            binding.sendOtp.isEnabled = true

        }
        userNameViewModel.isSetUserInfoToDB.observe(viewLifecycleOwner) {
            setInProgress(false)
            binding.sendOtp.isEnabled = true
            Toast.makeText(requireContext(), "UserName Set Successfully ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isUserNameAvailable(userInfo: UserInfo?) {
        setInProgress(true)
        binding.sendOtp.isEnabled = false
        val newUserName = binding.userName.text.toString()
        if (userInfo != null) {
            userNameViewModel.setCurrentUserDetails(currentDocumentRef, userInfo, newUserName, null)
        } else {
            val newUserInfo = UserInfo(newUserName, phoneNo, Timestamp.now())
            userNameViewModel.setCurrentUserDetails(currentDocumentRef, null, null, newUserInfo)

        }
    }

    private fun setInProgress(isProgressBar: Boolean) {
        binding.progressBar.isVisible = isProgressBar
        binding.sendOtp.isEnabled = isProgressBar
    }
}