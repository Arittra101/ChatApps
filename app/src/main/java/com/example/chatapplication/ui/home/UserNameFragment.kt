package com.example.chatapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplication.R
import com.example.chatapplication.data.model.UserInfo
import com.example.chatapplication.data.repository.userinfo.UserInfoRepositoryImp
import com.example.chatapplication.databinding.FragmentUserNameBinding
import com.example.chatapplication.ui.viewmodel.UserNameViewModelFactory
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference


class UserNameFragment : Fragment(R.layout.fragment_user_name) {

    private lateinit var binding: FragmentUserNameBinding
    lateinit private var currentDocumentRef: DocumentReference
    lateinit private var phoneNo: String
    lateinit private var currentUserId: String
    private var currentUserDetails: UserInfo? = null

    private lateinit var userNameViewModel: UserNameViewModel
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
            UserNameViewModelFactory(userInfoRep))[UserNameViewModel::class.java]

        initialize()

        binding.sendOtp.setOnClickListener {
            initialize()
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
            isUserNameAvailable(currentUserDetails)
        }
    }

    private fun isUserNameAvailable(userInfo: UserInfo?) {
        val newUserName = binding.userName.text.toString()
        if (userInfo != null) {
            userNameViewModel.setCurrentUserDetails(currentDocumentRef, userInfo, newUserName, null)
        } else {
            val newUserInfo = UserInfo(newUserName, phoneNo, Timestamp.now())
            userNameViewModel.setCurrentUserDetails(currentDocumentRef, null, null, newUserInfo)

        }
    }

    private fun getUserName() {
        setInProgress(true)
    }

    private fun setInProgress(isProgressBar: Boolean) {
        binding.progressBar.isVisible = isProgressBar
        binding.sendOtp.isEnabled = isProgressBar
    }
}