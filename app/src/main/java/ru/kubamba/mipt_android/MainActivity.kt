package ru.kubamba.mipt_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.kubamba.mipt_android.screens.restaurants.RestaurantsScreen
import ru.kubamba.mipt_android.ui.theme.MiptandroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiptandroidTheme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    RestaurantsScreen()
                }
            }
        }
    }
}

