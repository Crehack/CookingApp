package app.projet.navigation

import AddRecipeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.projet.screens.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }
        composable("settings") {
            SettingsScreen(
                isDarkMode = isDarkTheme,
                onThemeChange = onThemeChange,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("details/{nom}") { backStackEntry ->
            val nom = backStackEntry.arguments?.getString("nom") ?: ""
            RecipeDetailScreen(recipeName = nom)
        }
        composable("add_recipe") { AddRecipeScreen(navController) }
    }
}
