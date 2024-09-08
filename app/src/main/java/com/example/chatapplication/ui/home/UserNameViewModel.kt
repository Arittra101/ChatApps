package com.example.chatapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.chatapplication.data.model.UserInfo

import com.example.chatapplication.data.repository.userinfo.UserInfoRepository
import com.google.firebase.firestore.DocumentReference

class UserNameViewModel(private val userInfoRep: UserInfoRepository) : ViewModel() {
    val currentUserId: LiveData<String> = userInfoRep.currentUserId
    val currentUserDocumentReference: LiveData<DocumentReference> =
        userInfoRep.currentUserDocumentReference
    val currentUserDetails: LiveData<UserInfo?> = userInfoRep.currentUserDetails


    fun getCurrentUserId() {
        userInfoRep.getCurrentUserId()
    }

    fun getCurrentUserDocumentReference(userId: String) {
        userInfoRep.getCurrentUserDocumentReference(userId)
    }

    fun setCurrentUserDetails(
        userDocumentReference: DocumentReference, userInfo: UserInfo?,
        name: String?,
        newUserInfo: UserInfo?
    ) {
        userInfoRep.setCurrentUserDetails(userDocumentReference, userInfo, name, newUserInfo)
    }

    fun getCurrentUserDetails(userDocumentReference: DocumentReference) {
        userInfoRep.getCurrentDetails(userDocumentReference)
    }

}