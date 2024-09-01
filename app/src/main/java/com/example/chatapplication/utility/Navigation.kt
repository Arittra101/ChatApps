package com.example.chatapplication.utility

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

object Navigation {

    fun navigate(fragment: Fragment, from: Int? = null, to: Int, bundle: Bundle? = null) {
        val navController = fragment.findNavController()
        val navOptionsBuilder = NavOptions.Builder()

        if (from != null) {
            navOptionsBuilder.setPopUpTo(from, true)
        }

        val navOptions = navOptionsBuilder.build()
        navController.navigate(to, bundle, navOptions)
    }
}
