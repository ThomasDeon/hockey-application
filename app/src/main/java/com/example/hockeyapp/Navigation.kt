package com.example.hockeyapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.bottomNavigation.BottomNavigation
import com.example.hockeyapp.ui.signup.PrivacyScreen
import com.example.hockeyapp.ui.login.LoginScreen
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.PlayerHomepage
import com.example.hockeyapp.ui.playerPage.registration.RegisterPlayerScreen
import com.example.hockeyapp.ui.signup.PolicyScreen
import com.example.hockeyapp.ui.signup.SignUpScreen
import java.util.Objects


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
                    onLoginClick = {
                        navHostController.navigate(
                            Route.PlayerHome.route
                        ) {
                            popUpTo(route = "Login_flow")
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
