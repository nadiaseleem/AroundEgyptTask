package com.example.aroundegypt.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.aroundegypt.common.presentation.ui.theme.AroundEgyptTheme
import com.example.aroundegypt.common.presentation.ui_components.NavigationDrawerSheet
import com.example.aroundegypt.features.home.presentation.screens.experience.ExperienceRoute
import com.example.aroundegypt.features.home.presentation.screens.experience.ExperienceScreen
import com.example.aroundegypt.features.home.presentation.screens.home.HomeRoute
import com.example.aroundegypt.features.home.presentation.screens.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AroundEgyptTheme {
                NavigationDrawer()
            }
        }
    }
}

@Composable
private fun NavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        NavigationDrawerSheet() {
            coroutineScope.launch {
                drawerState.close()
            }
        }
    }) {
        AroundEgyptNavigation(drawerState)
    }
}

@Composable
private fun AroundEgyptNavigation(drawerState: DrawerState) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(drawerState = drawerState) {
                navController.navigate(ExperienceRoute(it))
            }
        }
        composable<ExperienceRoute> {
            val args = it.toRoute<ExperienceRoute>()
            ExperienceScreen(
                onBack = { navController.popBackStack() },
                id = args.id
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun NavigationDrawerPreview() {
    NavigationDrawer()
}