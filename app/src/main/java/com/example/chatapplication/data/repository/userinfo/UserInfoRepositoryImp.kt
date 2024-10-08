package com.example.chatapplication.data.repository.userinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatapplication.data.model.UserInfo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class UserInfoRepositoryImp() : UserInfoRepository {

    private val _currentUserId = MutableLiveData<String>()
    override val currentUserId: LiveData<String> get() = _currentUserId

    private val _currentUserDocumentReference = MutableLiveData<DocumentReference>()
    override val currentUserDocumentReference: LiveData<DocumentReference> get() = _currentUserDocumentReference

    private val _currentUserDetails = MutableLiveData<UserInfo?>()
    override val currentUserDetails: LiveData<UserInfo?> get() = _currentUserDetails

    private val _isSetUserInfoToDB = MutableLiveData<Boolean>()
    override val isSetUserInfoToDB : LiveData<Boolean> get() = _isSetUserInfoToDB

    private val _isUserLogIn = MutableLiveData<Boolean>()
    override val isUserLogIn : LiveData<Boolean> get() = _isUserLogIn




    override fun getCurrentUserId() {
        _currentUserId.postValue(FirebaseAuth.getInstance().uid.toString())
    }
    override fun checkUserLogIn() {
        if (Firebase.auth.currentUser != null) {
            _isUserLogIn.postValue(true)
        } else _isUserLogIn.postValue(false)
    }

    override fun getCurrentDetails(userDocumentReference: DocumentReference) {
        var userInfo: UserInfo

        userDocumentReference.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {
                    userInfo = UserInfo(
                        userName = document.getString("userName") ?: "",
                        phoneNo = document.getString("phoneNo") ?: "",
                        createdTimestamp = document.getTimestamp("createdTimestamp")
                            ?: Timestamp.now()
                    )
                    _currentUserDetails.postValue(userInfo)

                } else {

                    _currentUserDetails.postValue(null)
                    Log.d("getCurrentUserName", "User document does not exist")
                }
            } else {

                Log.e("getCurrentUserName", "Error getting user document", task.exception)
            }
        }
    }

    override fun getCurrentUserDocumentReference(currentUserId: String) {
        _currentUserDocumentReference.postValue(
            FirebaseFirestore.getInstance().collection("users").document(currentUserId)
        )
    }

    override fun setCurrentUserDetails(
        userDocumentReference: DocumentReference,
        userInfo: UserInfo?,
        name: String?,
        newUserInfo: UserInfo?
    ) {
        val dbUserInfo = when {
            newUserInfo==null && userInfo != null && name != null -> userInfo.apply { userName = name }
            newUserInfo != null -> newUserInfo
            else -> UserInfo("", "", Timestamp.now())
        }

        userDocumentReference.set(dbUserInfo).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _isSetUserInfoToDB.postValue(true)
                Log.d("setCurrentUserDetails", "User details updated successfully")
            } else {
                _isSetUserInfoToDB.postValue(false)

                Log.e("setCurrentUserDetails", "Error updating user details", task.exception)
            }
        }
    }


}