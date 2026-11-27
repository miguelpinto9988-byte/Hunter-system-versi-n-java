package com.huntersystem.nativo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONObject

@Composable
internal fun Profile(saved: String?, onSave: (String) -> Boolean) {
    val previous = remember(saved) { saved?.let { runCatching { JSONObject(it) }.getOrNull() } }
    var name by rememberSaveable { mutableStateOf(previous?.optString("name").orEmpty()) }
    var age by rememberSaveable { mutableStateOf(previous?.optString("age").orEmpty()) }
    var height by rememberSaveable { mutableStateOf(previous?.optString("height").orEmpty()) }
    var weight by rememberSaveable { mutableStateOf(previous?.optString("weight").orEmpty()) }
    var error by rememberSaveable { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("HUNTER SYSTEM", color = silver, fontFamily = displayFont, fontSize = 24.sp)
        Text(if (previous == null) "INICIALIZACIÓN DEL SISTEMA" else "FICHA DEL CAZADOR", fontFamily = bodyFont)
        Text(if (previous == null) "Tu próxima misión\nempieza contigo." else "Configura tu camino.", fontFamily = displayFont, fontSize = 34.sp)
        Text(if (previous == null) "Crea tu perfil" else "Datos y preferencias")
        ProfileField("Nombre de cazador", name, KeyboardType.Text) { name = it.take(35) }
        ProfileField("Edad", age, KeyboardType.Number) { age = it }
        ProfileField("Estatura · cm", height, KeyboardType.Decimal) { height = it }
        ProfileField("Peso corporal · kg", weight, KeyboardType.Decimal) { weight = it }
        if (error.isNotEmpty()) Text(error, color = MaterialTheme.colorScheme.error)
        Button(onClick = {
            val years = age.toIntOrNull()
            val cm = height.replace(',', '.').toDoubleOrNull()
            val kg = weight.replace(',', '.').toDoubleOrNull()
            error = when {
                name.isBlank() -> "Escribe tu nombre de cazador."
                years == null || years !in 13..100 -> "Revisa la edad."
                cm == null || cm !in 100.0..250.0 -> "Revisa la estatura en centímetros."
                kg == null || kg !in 30.0..350.0 -> "Revisa el peso en kilogramos."
                else -> {
                    val profile = JSONObject().put("name", name.trim()).put("age", years).put("height", cm).put("weight", kg)
                    if (onSave(profile.toString())) "" else "No se pudo guardar el perfil. Vuelve a intentarlo."
                }
            }
        }) { Text(if (previous == null) "DESPERTAR MI SISTEMA →" else "Guardar perfil") }
        Text("Datos guardados en este dispositivo.", fontFamily = bodyFont)
    }
}

@Composable
private fun ProfileField(label: String, value: String, type: KeyboardType, update: (String) -> Unit) {
    OutlinedTextField(value, update, Modifier.fillMaxWidth(), label = { Text(label) }, singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = type))
}
