package refixbody.fix.foodapplication.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHostContainer(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "food") {
        composable("food") { MainFoodScreen() }
        composable("person") { MainPersonScreen() }
    }
}