package app.projet.navigation

import AddRecipeScreen
import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.projet.collection.GestionnaireRecettes
import app.projet.screens.*

@Composable
fun AppNavigation(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    context: Context // Passer context en paramètre ici
) {
    // Initialisation des recettes dès que possible
    GestionnaireRecettes.init(context)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController,
                context = context // Passer context à chaque écran
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
            RecipeDetailScreen(recipeName = nom, navController = navController)
        }

        composable("add_recipe") { AddRecipeScreen(navController = navController, context = context) }

        composable("edit_recipe/{nom}") { backStackEntry ->
            val nom = backStackEntry.arguments?.getString("nom") ?: ""
            if (nom.isNotEmpty()) {
                EditRecipeScreen(recipeName = nom, navController = navController, context = context)
            } else {
                Text("Recette introuvable")
            }
        }
    }
}
