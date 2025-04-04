package app.projet.models

data class Recette(
    val nom: String,
    val ingredients: List<Ingredient>,
    val instructions: String,
    val categorie: String
)
