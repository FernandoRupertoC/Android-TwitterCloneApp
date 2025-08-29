package com.example.capacitacionandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.capacitacionandroid.ui.theme.CapacitacionAndroidTheme
import com.example.capacitacionandroid.ui.view.auth.login.LoginScreen
import com.example.capacitacionandroid.ui.view.auth.register.RegisterScreen
import com.example.capacitacionandroid.ui.view.core.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapacitacionAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavigationWrapper()
                    }
                }
            }
        }
    }
}

