package app.projet.collection

import app.projet.models.Recette

object GestionnaireRecettes {
    val recettes = mutableListOf<Recette>()

    fun ajouterRecette(recette: Recette) {
        recettes.add(recette)
    }
}
