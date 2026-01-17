package com.example.apiintegration

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.apiintegration.ui.HomeFragment
import com.example.apiintegration.ui.RickMortyFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add fragment in activity
//        if(savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, HomeFragment())
//                .commit()
//        }

        val navHomeFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment

        val navController = navHomeFragment.navController
    }
}