package com.example.hockeyapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.signup.PrivacyScreen
import com.example.hockeyapp.ui.login.LoginScreen
import com.example.hockeyapp.ui.signup.PolicyScreen
import com.example.hockeyapp.ui.signup.SignUpScreen


sealed class Route{
    data class LoginScreen(val name: String = "Login"): Route()
    data class SignUpScreen(val name: String = "SignUp"): Route()
    data class PrivacyScreen(val name: String = "Privacy"): Route()
    data class PolicyScreen(val name: String = "Policy"): Route()
    data class HomeScreen(val name: String = "Home"): Route()
}

@Composable
fun MyNavigation(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = "Login_flow")
    {
        navigation(startDestination = Route.LoginScreen().name,route = "Login_flow") {

            composable(route = Route.LoginScreen().name) {
                LoginScreen(
                    onLoginClick = {
                        navHostController.navigate(
                            Route.HomeScreen().name
                        ) {
                            popUpTo(route = "Login_flow")
                        }
                    },
                    onSignUpClick = {
                        navHostController.navigate(
                            Route.SignUpScreen().name
                        )
                    }
                )
            }
            composable(route = Route.SignUpScreen().name) {
                SignUpScreen(
                    onSignUpClick = {
                        navHostController.navigate(
                            Route.LoginScreen().name
                        )
                    },
                    onLoginClick = {
                        navHostController.navigate(Route.LoginScreen().name) {
                            popUpTo("Login_flow") {
                                inclusive = true // 💥 Clears all the way up to Login_flow
                            }
                            launchSingleTop = true // Avoid creating duplicate LoginScreen
                        }
                    },
                    onPolicyClicked = {
                        navHostController.navigate(
                            Route.PolicyScreen().name
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onPrivacyClicked = {
                        navHostController.navigate(
                            Route.PrivacyScreen().name
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(route = Route.PrivacyScreen().name) {
                PrivacyScreen {
                    navHostController.navigateUp()
                }
            }
            composable(route = Route.PolicyScreen().name) {
                PolicyScreen {
                    navHostController.navigateUp()
                }
            }
        }
            composable(route = Route.HomeScreen().name) {
                HomeScreen()
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