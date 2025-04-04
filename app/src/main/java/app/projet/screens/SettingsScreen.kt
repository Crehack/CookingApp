package app.projet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    isDarkMode: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Paramètres", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Mode sombre")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isDarkMode,
                onCheckedChange = { onThemeChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = onNavigateBack) {
            Text("Retour")
        }
    }
}
