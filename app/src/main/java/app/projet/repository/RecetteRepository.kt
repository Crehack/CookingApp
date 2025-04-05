package app.projet.repository

import android.content.Context
import android.content.SharedPreferences
import app.projet.models.Recette
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RecetteRepository {

    private const val PREFS_NAME = "recettes_prefs"
    private const val RECETTES_KEY = "recettes"

    // Sauvegarde des recettes dans SharedPreferences
    fun saveRecettes(context: Context, recettes: List<Recette>) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(recettes)
        editor.putString(RECETTES_KEY, json)
        editor.apply()
    }

    // Chargement des recettes depuis SharedPreferences
    fun loadRecettes(context: Context): MutableList<Recette> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(RECETTES_KEY, null)

        val type = object : TypeToken<List<Recette>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf() // Liste vide si aucune recette n'est sauvegardée
        }
    }
}
