package com.example.hockeyapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.ui.bottomNavigation.BottomNavigation
import com.example.hockeyapp.ui.login.LoginScreen
import com.example.hockeyapp.ui.signup.PolicyScreen
import com.example.hockeyapp.ui.signup.PrivacyScreen
import com.example.hockeyapp.ui.signup.SignUpScreen



sealed class Route(val route: String) {
    object Login : Route("Login")
    object SignUp : Route("SignUp")
    object Privacy : Route("Privacy")
    object Policy : Route("Policy")
    object Home : Route("Home")
    object News : Route("News")
    object Profile : Route("Profile")
    object Setting : Route("Setting")
    object RegisterTeam : Route("RegisterTeam")
    object PlayerHome: Route("playerHome")
    object PlayerRegister : Route("playerRegister")
    object healthFitness: Route("health")
    object Event : Route("playerEvent")
    object YouTubeVid : Route(" utubevid" )
    object Coach: Route( "coach")
    object Team : Route ("team")
    object Player : Route("player")
}





@Composable
fun MyNavigation(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = "Login_flow")
    {
        navigation(startDestination = Route.Login.route,route = "Login_flow") {

            composable(route = Route.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        navHostController.navigate(Route.PlayerHome.route) {
                            popUpTo("Login_flow") {inclusive = true}
                            launchSingleTop = true
                        }
                    },
                    onSignUpClick = {
                        navHostController.navigate(
                            Route.SignUp.route
                        )
                    }
                )
            }
            composable(route = Route.SignUp.route) {
                SignUpScreen(
                    onSignUpClick = {
                        navHostController.navigate(
                            Route.Login.route
                        )
                    },
                    onLoginClick = {
                        navHostController.navigate(Route.Login.route) {
                            popUpTo("Login_flow") {
                                inclusive = true // 💥 Clears all the way up to Login_flow
                            }
                            launchSingleTop = true // Avoid creating duplicate LoginScreen
                        }
                    },
                    onPolicyClicked = {
                        navHostController.navigate(
                            Route.Policy.route
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onPrivacyClicked = {
                        navHostController.navigate(
                            Route.Privacy.route
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onSignUpSuccess = {
                        navHostController.navigate(Route.Login.route){
                            popUpTo("Login_flow") { inclusive = true }
                            launchSingleTop = true
                        }
                    }

                )
            }
            composable(route = Route.Privacy.route) {
                PrivacyScreen {
                    navHostController.navigateUp()
                }
            }
            composable(route = Route.Policy.route) {
                PolicyScreen {
                    navHostController.navigateUp()
                }
            }
        }

        composable(route = Route.PlayerHome.route) {
            BottomNavigation()


        }


    }
}



        fun NavController.navigateToSingleTop(route: String){
            navigate(route){
                popUpTo(graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
