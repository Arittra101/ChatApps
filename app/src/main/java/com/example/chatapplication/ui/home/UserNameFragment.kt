package com.example.chatapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.chatapplication.R
import com.example.chatapplication.databinding.FragmentUserNameBinding
import com.google.firebase.auth.FirebaseAuth


class UserNameFragment : Fragment(R.layout.fragment_user_name) {

    private lateinit var binding: FragmentUserNameBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentUserNameBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


    }

}