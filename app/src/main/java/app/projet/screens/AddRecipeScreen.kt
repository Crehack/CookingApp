import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.projet.collection.GestionnaireRecettes
import app.projet.models.Ingredient
import app.projet.models.Recette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(navController: NavController , context: Context) {
    var recipeName by remember { mutableStateOf("") }
    var recetteInstructions by remember { mutableStateOf("") }
    var recetteCategorie by remember { mutableStateOf("") }
    var ingredientsList by remember { mutableStateOf(mutableListOf<Ingredient>()) }
    var errorMessage by remember { mutableStateOf("") }

    // Formulaire pour ajouter un ingrédient
    var ingredientName by remember { mutableStateOf("") }
    var ingredientQuantity by remember { mutableStateOf("") }
    var ingredientUnit by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Ajouter une recette") }) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            TextField(
                value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Nom de la recette") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = recetteInstructions,
                onValueChange = { recetteInstructions = it },
                label = { Text("Instructions de la recette") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = recetteCategorie,
                onValueChange = { recetteCategorie = it },
                label = { Text("Catégorie") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ajouter un ingrédient
            Text("Ajouter un ingrédient", style = MaterialTheme.typography.titleMedium)

            TextField(
                value = ingredientName,
                onValueChange = { ingredientName = it },
                label = { Text("Nom de l'ingrédient") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = ingredientQuantity,
                onValueChange = { ingredientQuantity = it },
                label = { Text("Quantité") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = ingredientUnit,
                onValueChange = { ingredientUnit = it },
                label = { Text("Unité de mesure") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Affichage des erreurs éventuelles
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }

            Button(onClick = {
                if (ingredientName.isNotEmpty() && ingredientQuantity.isNotEmpty() && ingredientUnit.isNotEmpty()) {
                    try {
                        val quantity = ingredientQuantity.toFloat()
                        ingredientsList.add(Ingredient(ingredientName, quantity, ingredientUnit))
                        ingredientName = ""
                        ingredientQuantity = ""
                        ingredientUnit = ""
                        errorMessage = "" // Clear error message
                    } catch (e: NumberFormatException) {
                        errorMessage = "La quantité doit être un nombre valide"
                    }
                } else {
                    errorMessage = "Tous les champs doivent être remplis"
                }
            }) {
                Text("Ajouter l'ingrédient")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Afficher la liste des ingrédients ajoutés
            LazyColumn {
                items(ingredientsList.size) { index ->
                    Text("Ingrédient ${index + 1}: ${ingredientsList[index].nom} - ${ingredientsList[index].quantite} ${ingredientsList[index].unite}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (recipeName.isNotEmpty() && recetteInstructions.isNotEmpty() && recetteCategorie.isNotEmpty() && ingredientsList.isNotEmpty()) {
                    val newRecette = Recette(
                        nom = recipeName,
                        ingredients = ingredientsList,
                        instructions = recetteInstructions,
                        categorie = recetteCategorie
                    )

                    // Ajouter la recette dans GestionnaireRecettes et sauvegarder
                    GestionnaireRecettes.ajouterRecette(context , newRecette)
                    GestionnaireRecettes.sauvegarderRecettes(context) // Passer context pour sauvegarder
                    navController.popBackStack()  // Retourner à la page précédente
                } else {
                    errorMessage = "Tous les champs de la recette doivent être remplis"
                }
            }) {
                Text("Sauvegarder la recette")
            }
        }
    }
}
