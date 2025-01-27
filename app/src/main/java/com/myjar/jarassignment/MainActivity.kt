package com.myjar.jarassignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.myjar.jarassignment.ui.vm.JarViewModel
import com.myjar.jarassignment.ui.composables.AppNavigation
import com.myjar.jarassignment.ui.theme.JarAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<JarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                viewModel.fetchData()
            }

            LaunchedEffect(Unit) {
                viewModel.cachedDataEvent.collect { isCached ->
                    if (isCached) {
                        Toast.makeText(
                            this@MainActivity,
                            "Showing cached version",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            JarAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}