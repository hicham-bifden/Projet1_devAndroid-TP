// HomeActivity.kt
package com.example.projet1_devandroid_tp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen()  // Affiche l'écran d'accueil
        }
    }
}

