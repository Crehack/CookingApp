package app.projet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import app.projet.navigation.AppNavigation
import app.projet.ui.theme.AppProjetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkMode by remember { mutableStateOf(false) }

            AppProjetTheme(darkTheme = darkMode) {
                val navController = rememberNavController()

                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(
                        navController = navController,
                        isDarkTheme = darkMode,
                        onThemeChange = { darkMode = it }
                    )
                }
            }
        }
    }
}
