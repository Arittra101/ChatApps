package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapplication.databinding.ActivityHomeActivityBinding
import com.example.chatapplication.ui.navigation.Navigation
import com.example.chatapplication.utility.AuthManager


class VerificationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityHomeActivityBinding
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        if(AuthManager.isLoggedIn(this)){
            val intent: Intent = Intent(
                this@VerificationActivity,
                SocialActivity::class.java
            )
            finish()
            startActivity(intent)
        }

    }

}