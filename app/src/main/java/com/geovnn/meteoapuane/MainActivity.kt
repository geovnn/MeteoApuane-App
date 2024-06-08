package com.geovnn.meteoapuane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.geovnn.meteoapuane.presentation.Navigation
import com.geovnn.meteoapuane.ui.theme.MeteoApuaneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeteoApuaneTheme {
                Navigation()
            }
        }
    }
}

