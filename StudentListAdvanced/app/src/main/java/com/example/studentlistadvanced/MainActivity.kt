package com.example.studentlistadvanced

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.fragmentNavHost)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
//        val navController = navHostFragment.navController
    }
}