package app.projet.data

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(val nom: String, val quantite: String, val unite: String)
