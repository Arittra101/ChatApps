package com.example.chatapplication.data.model

import com.google.firebase.Timestamp

data class UserInfo(
    var userName : String,
    val phoneNo : String,
    val createdTimestamp : Timestamp
)
