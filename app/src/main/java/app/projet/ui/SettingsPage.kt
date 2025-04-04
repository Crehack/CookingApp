package app.projet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPage(onThemeChange: (Boolean) -> Unit, isDarkMode: Boolean, onNavigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Paramètres", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Mode sombre")
        Switch(
            checked = isDarkMode,
            onCheckedChange = {
                onThemeChange(it)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNavigateBack) {
            Text("Retour")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SettingsPagePreview() {
    SettingsPage(onThemeChange = {}, isDarkMode = false, onNavigateBack = {})
}