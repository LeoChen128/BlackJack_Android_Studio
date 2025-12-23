package com.example.project_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project_2.ui.theme.Project_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataManager.initialize(this)

        enableEdgeToEdge()
        setContent {
            Project_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BlackjackApp()
                }
            }
        }
    }
}

@Composable
fun BlackjackApp() {
    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller, startDestination = "start") {
        composable("start") {
            StartScreen(navController = navcontroller)
        }
        composable("game") {
            GameScreen(navController = navcontroller)
        }
    }
}