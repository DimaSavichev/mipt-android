package ru.kubamba.mipt_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.kubamba.mipt_android.screens.restaurants.RestaurantDetailScreen
import ru.kubamba.mipt_android.screens.restaurants.RestaurantsScreen
import ru.kubamba.mipt_android.screens.restaurants.RestaurantsViewModel
import ru.kubamba.mipt_android.screens.signin.SignInScreen
import ru.kubamba.mipt_android.screens.signin.SignInViewModel
import ru.kubamba.mipt_android.ui.theme.MiptandroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MiptandroidTheme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "signin") {
                        composable("signin") {
                            val signInViewModel: SignInViewModel = viewModel()
                            SignInScreen(signInViewModel = signInViewModel, navController = navController)
                        }

                        composable("restaurants") {
                            val restaurantsViewModel: RestaurantsViewModel = hiltViewModel<RestaurantsViewModel>()

                            RestaurantsScreen(
                                restaurantsViewModel = restaurantsViewModel,
                                navController = navController)
                        }

                        composable("detail/{name}") { backStackEntry ->
                            RestaurantDetailScreen(backStackEntry.arguments?.getString("name").orEmpty())
                        }
                    }
                }
            }
        }
    }
}

