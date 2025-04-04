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
fun AddRecipeScreen(navController: NavController) {
    var recipeName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Ajouter une recette") }) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            TextField(
                value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Nom de la recette") }
            )
            Button(onClick = {
                if (recipeName.isNotEmpty()) {
                    GestionnaireRecettes.ajouterRecette(recipeName)
                    navController.popBackStack()
                }
            }) {
                Text("Ajouter")
            }
        }
    }
}