package app.projet.data


import kotlinx.serialization.Serializable

@Serializable
data class Recette(var nom: String, val ingredients: MutableList<Ingredient>)
