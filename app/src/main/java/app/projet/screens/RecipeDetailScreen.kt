package app.projet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.projet.collection.GestionnaireRecettes
import app.projet.models.Recette

@Composable
fun RecipeDetailScreen(recipeName: String, navController: NavController) {
    val recette = GestionnaireRecettes.recettes.find { it.nom == recipeName }

    if (recette == null) {
        Text("Recette introuvable.")
        return
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(recette.nom, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Catégorie : ${recette.categorie}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Ingrédients :", style = MaterialTheme.typography.titleMedium)
        recette.ingredients.forEach {
            Text("- ${it.nom} : ${it.quantite} ${it.unite}")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Instructions :", style = MaterialTheme.typography.titleMedium)
        Text(recette.instructions)

        Spacer(modifier = Modifier.height(32.dp))

        // Boutons Modifier / Supprimer
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                navController.navigate("edit_recipe/${recette.nom}")
            }) {
                Text("Modifier")
            }

            OutlinedButton(
                onClick = {
                    GestionnaireRecettes.recettes.remove(recette)
                    GestionnaireRecettes.sauvegarderRecettes(navController.context)  // Sauvegarder après suppression
                    navController.popBackStack() // Retour à l'accueil
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Supprimer")
            }
        }
    }
}
