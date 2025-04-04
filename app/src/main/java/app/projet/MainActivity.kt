package app.projet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import app.projet.ui.HomeScreen
import app.projet.ui.SettingsPage
import app.projet.ui.theme.AppProjetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkMode by remember { mutableStateOf(false) }

            AppProjetTheme(darkTheme = darkMode) {
                val navController = rememberNavController()

                // Configuration de la navigation
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onNavigateToSettings = { navController.navigate("settings") },
                            onThemeChange = { darkMode = it }
                        )
                    }
                    composable("settings") {
                        SettingsPage(
                            onThemeChange = { darkMode = it },
                            isDarkMode = darkMode,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MainActivity()
}