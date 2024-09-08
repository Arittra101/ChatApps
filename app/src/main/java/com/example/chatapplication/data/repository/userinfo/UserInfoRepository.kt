package com.example.chatapplication.data.repository.userinfo

import androidx.lifecycle.LiveData
import com.example.chatapplication.data.model.UserInfo
import com.google.firebase.firestore.DocumentReference

interface UserInfoRepository {
    val currentUserId: LiveData<String>
    val currentUserDocumentReference: LiveData<DocumentReference>
    val currentUserDetails: LiveData<UserInfo?>
    val isSetUserInfoToDB : LiveData<Boolean>

    fun getCurrentUserId()
    fun getCurrentDetails(userDocumentReference: DocumentReference)
    fun getCurrentUserDocumentReference(currentUserId: String)

    fun setCurrentUserDetails(userDocumentReference : DocumentReference,userInfo :UserInfo?,name:String?=null,newUserInfo: UserInfo?=null)

}