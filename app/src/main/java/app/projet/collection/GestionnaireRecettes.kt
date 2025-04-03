package app.projet.collection

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.*
import java.io.File

object GestionnaireRecettes {
    private const val DATA_FILE = "donnees_plats.json"
    private val json = Json { prettyPrint = true }
    var recettes: MutableList<Recette> = chargerRecettes()

    // Charger les recettes depuis le fichier JSON
    private fun chargerRecettes(): MutableList<Recette> {
        val file = File(DATA_FILE)
        return if (file.exists()) {
            json.decodeFromString(file.readText())
        } else {
            mutableListOf()
        }
    }

    // Sauvegarder les recettes dans le fichier JSON
    private fun sauvegarderRecettes() {
        File(DATA_FILE).writeText(json.encodeToString(ListSerializer(Recette.serializer()), recettes))
    }

    // Ajouter une nouvelle recette
    fun ajouterRecette(nom: String) {
        if (recettes.any { it.nom == nom }) {
            println("⚠️ Recette déjà existante.")
        } else {
            recettes.add(Recette(nom, mutableListOf()))
            sauvegarderRecettes()
        }
    }

    // Supprimer une recette
    fun supprimerRecette(nom: String) {
        recettes.removeIf { it.nom == nom }
        sauvegarderRecettes()
    }

    // Afficher les recettes (retourner les noms de recettes sous forme de liste)
    fun afficherRecettes(): List<String> {
        return recettes.map { it.nom }
    }

    // Modifier une recette (changer son nom)
    fun modifierRecette(nom: String, nouveauNom: String) {
        val recette = recettes.find { it.nom == nom }
        if (recette != null) {
            recette.nom = nouveauNom
            sauvegarderRecettes()
        }
    }

    // Ajouter un ingrédient à une recette
    fun ajouterIngredeient(nomRecette: String, ingr: Ingredient) {
        val recette = recettes.find { it.nom == nomRecette }
        if (recette != null) {
            recette.ingredients.add(ingr)
            sauvegarderRecettes()
        }
    }

    // Sélectionner un plat au hasard parmi les recettes
    fun choisirPlatAuHasard(): Recette? {
        return if (recettes.isNotEmpty()) {
            recettes.random()
        } else {
            null
        }
    }
}
