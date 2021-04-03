package com.example.designday


import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
sealed class Screen(val route: String, val name:String, val icon: Int? = null){
    object Welcome : Screen("welcome_screen", "Welcome")
    object Login : Screen("login_screen", "Login")
    object Home : Screen("home_screen", "Home", R.drawable.spa)
    object Profile : Screen("Profile_screen", "Profile", R.drawable.account_circle)

}

@Composable
fun Navx() {
   val navController = rememberNavController()
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
        builder = { composable(Screen.Welcome.route){
            Welcome(navController)
        }
            composable(Screen.Login.route){
                Login(navController)
            }
            composable(Screen.Home.route){
                Home(navController)
            }

        })

}