package app.projet

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.projet.collection.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuisineAssistantApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuisineAssistantApp() {
    val recetteName by remember { mutableStateOf(TextFieldValue("")) }
    val ingredientName by remember { mutableStateOf(TextFieldValue("")) }
    var recetteList by remember { mutableStateOf(GestionnaireRecettes.afficherRecettes()) }
    val context = LocalContext.current // Contexte pour afficher le Toast

    // Interface utilisateur
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🍳 Assistant de Cuisine") })
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp).padding(top = 64.dp)
            ) { // Ajout d'un padding supplémentaire pour éviter la superposition avec la TopAppBar
                // Interface avec boutons pour les différentes actions
                Button(onClick = {
                    // Ajouter une recette
                    if (recetteName.text.isNotEmpty()) {
                        GestionnaireRecettes.ajouterRecette(recetteName.text)
                        recetteList = GestionnaireRecettes.afficherRecettes()
                        showToast(context, "Recette ajoutée : ${recetteName.text}")
                    } else {
                        showToast(context, "Nom de recette invalide")
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Ajouter une recette")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    // Afficher toutes les recettes
                    showToast(context, "Recettes disponibles : ${recetteList.joinToString()}")
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Afficher toutes les recettes")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    // Supprimer une recette
                    if (recetteName.text.isNotEmpty()) {
                        GestionnaireRecettes.supprimerRecette(recetteName.text)
                        recetteList = GestionnaireRecettes.afficherRecettes()
                        showToast(context, "Recette supprimée : ${recetteName.text}")
                    } else {
                        showToast(context, "Nom de recette invalide")
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Supprimer une recette")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    // Modifier une recette
                    if (recetteName.text.isNotEmpty() && ingredientName.text.isNotEmpty()) {
                        GestionnaireRecettes.modifierRecette(recetteName.text, ingredientName.text)
                        recetteList = GestionnaireRecettes.afficherRecettes()
                        showToast(context, "Recette modifiée")
                    } else {
                        showToast(context, "Données invalides")
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Modifier une recette")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    // Ajouter un ingrédient
                    if (recetteName.text.isNotEmpty() && ingredientName.text.isNotEmpty()) {
                        val ingredient = Ingredient(ingredientName.text, "1", "unité")
                        GestionnaireRecettes.ajouterIngredeient(recetteName.text, ingredient)
                        recetteList = GestionnaireRecettes.afficherRecettes()
                        showToast(context, "Ingrédient ajouté : ${ingredientName.text}")
                    } else {
                        showToast(context, "Données invalides")
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Ajouter un ingrédient")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    // Choisir un plat au hasard
                    val platChoisi = GestionnaireRecettes.choisirPlatAuHasard()
                    if (platChoisi != null) {
                        showToast(context, "Plat choisi au hasard: ${platChoisi.nom}")
                    } else {
                        showToast(context, "Aucune recette disponible")
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Choisir un plat au hasard")
                }
            }
        }
    )
}

// Fonction pour afficher un Toast
fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun PreviewCuisineAssistantApp() {
    CuisineAssistantApp()
}