package app.projet.data

import kotlinx.serialization.builtins.ListSerializer
import java.io.File
import kotlinx.serialization.json.*

object GestionnaireRecettes {
    private const val DATA_FILE = "donnees_plats.json"
    private val json = Json { prettyPrint = true }
    var recettes: MutableList<Recette> = chargerRecettes()

    private fun chargerRecettes(): MutableList<Recette> {
        val file = File(DATA_FILE)
        return if (file.exists()) {
            json.decodeFromString(file.readText())
        } else {
            mutableListOf()
        }
    }

    private fun sauvegarderRecettes() {
        File(DATA_FILE).writeText(json.encodeToString(ListSerializer(Recette.serializer()), recettes))
    }

    fun ajouterRecette(nom: String) {
        if (recettes.none { it.nom == nom }) {
            recettes.add(Recette(nom, mutableListOf()))
            sauvegarderRecettes()
        }
    }
}
