package app.projet

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.projet.ui.HomeScreen
import app.projet.screens.RecipeScreen
import app.projet.screens.AddRecipeScreen

/*
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navControler) }
        composable("recipe/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            RecipeScreen(navController, recipeName)
        }
        composable("addRecipe") { AddRecipeScreen(navController) }
    }
}
*/