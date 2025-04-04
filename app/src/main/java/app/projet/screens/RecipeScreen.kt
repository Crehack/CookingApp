package app.projet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.projet.data.GestionnaireRecettes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(navController: NavController, recipeName: String) {
    val recette = GestionnaireRecettes.recettes.find { it.nom == recipeName }

    Scaffold(
        topBar = { TopAppBar(title = { Text(recipeName) }) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            recette?.let {
                Text("Ingrédients :", style = MaterialTheme.typography.titleLarge)
                it.ingredients.forEach { ingredient ->
                    Text("- ${ingredient.nom}")
                }
            } ?: Text("Recette introuvable.")
        }
    }
}
