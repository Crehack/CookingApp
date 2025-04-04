package app.projet.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToSettings: () -> Unit, onThemeChange: (Boolean) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🍳 Assistant de Cuisine") })
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Bienvenue dans l'Assistant de Cuisine !")

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onNavigateToSettings,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Paramètres")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToSettings = {},
        onThemeChange = {}
    )
}
