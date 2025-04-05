package app.projet.collection

import android.content.Context
import app.projet.models.Recette
import app.projet.repository.RecetteRepository

object GestionnaireRecettes {

    var recettes = mutableListOf<Recette>()

    fun init(context: Context) {
        recettes = RecetteRepository.loadRecettes(context)
    }

    // Fonction pour ajouter une recette
    fun ajouterRecette(context: Context , recette: Recette) {
        recettes.add(recette)
        RecetteRepository.saveRecettes(context, recettes)
    }

    // Fonction pour charger les recettes depuis le stockage local
    fun chargerRecettes(context: Context) {
        recettes = RecetteRepository.loadRecettes(context)
    }

    // Fonction pour sauvegarder les recettes dans le stockage local
    fun sauvegarderRecettes(context: Context) {
        RecetteRepository.saveRecettes(context, recettes)
    }
}
