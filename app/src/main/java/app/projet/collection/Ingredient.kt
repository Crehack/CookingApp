package app.projet.collection

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(val nom: String, val quantite: String, val unite: String)
