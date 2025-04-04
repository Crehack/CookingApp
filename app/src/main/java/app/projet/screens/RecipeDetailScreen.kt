package app.projet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.projet.collection.GestionnaireRecettes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(recipeName: String) {
    val recette = GestionnaireRecettes.recettes.find { it.nom == recipeName }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(recipeName) })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            recette?.let {
                Text("Ingrédients :", style = MaterialTheme.typography.titleLarge)
                it.ingredients.forEach { ingredient ->
                    Text("- ${ingredient.nom}")
                }
            } ?: Text("Recette introuvable.")
        }
    }
}
