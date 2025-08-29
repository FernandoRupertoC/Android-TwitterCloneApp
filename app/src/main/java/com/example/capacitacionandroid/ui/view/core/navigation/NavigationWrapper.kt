package com.example.capacitacionandroid.ui.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capacitacionandroid.ui.view.auth.login.LoginScreen
import com.example.capacitacionandroid.ui.view.auth.register.RegisterScreen
import com.example.capacitacionandroid.ui.view.following.FollowingScreen
import com.example.capacitacionandroid.ui.view.following.FollowingViewModel
import com.example.capacitacionandroid.ui.view.following.UserContainer
import com.example.capacitacionandroid.ui.view.home.ForYouViewModel
import com.example.capacitacionandroid.ui.view.home.HomeScreen
import com.example.capacitacionandroid.ui.view.timelineSettings.TimelineScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {

        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Home) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                navigateToRegister = {
                    navController.navigate(Register)
                }
            )
        }

        composable<Register> {
            RegisterScreen(
                navigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable<Home> {
//            val factory = FollowingViewModel.UserViewModelFactory(UserContainer.useCase)
//            val viewModel: FollowingViewModel = viewModel(factory = factory)
//            HomeScreen(viewModel, navController)

                // FollowingViewModel
                val factory = FollowingViewModel.UserViewModelFactory(UserContainer.useCase)
                val followingVM: FollowingViewModel = viewModel(factory = factory)

                // ForYouViewModel
                val forYouVM: ForYouViewModel = viewModel()

                // Llamada correcta
                HomeScreen(followingViewModel = followingVM, forYouViewModel = forYouVM, navController = navController)


        }

        composable<Following> {
            val factory = FollowingViewModel.UserViewModelFactory(UserContainer.useCase)
            val viewModel: FollowingViewModel = viewModel(factory = factory)

            FollowingScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<Timeline> {

            TimelineScreen(
                navigateUp = {
                    navController.navigate(Home) {
                        popUpTo(Home) { inclusive = false }
                        launchSingleTop = true
                    }
                }

            )
        }


    }
}



