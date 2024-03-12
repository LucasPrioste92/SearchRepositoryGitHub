package com.lucasprioste.searchrepositorygithub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lucasprioste.searchrepositorygithub.presentation.core.navigation.Navigation
import com.lucasprioste.searchrepositorygithub.presentation.ui.theme.SearchRepositoryGitHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchRepositoryGitHubTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(
                        navController = navController
                    )
                }
            }
        }
    }
}