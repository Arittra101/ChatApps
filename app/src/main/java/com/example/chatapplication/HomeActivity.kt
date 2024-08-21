package com.example.chatapplication
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapplication.R
import com.example.chatapplication.databinding.ActivityHomeActivityBinding
import com.google.firebase.firestore.FirebaseFirestore


class HomeActivity : AppCompatActivity() {


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

        val data = mutableMapOf<String, String>()
        FirebaseFirestore.getInstance().collection("test").add(data)

//        val l = 1
//        // Setup navigation
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController: NavController = navHostFragment.navController
//        val navInflater: NavInflater = navController.navInflater
//        val navGraph: NavGraph = navInflater.inflate(R.navigation.nav_graph)
//
//        if (l==1) {
//            navGraph.setStartDestination(R.id.signFragment)
//        }
//
//        navController.graph = navGraph



    }

}