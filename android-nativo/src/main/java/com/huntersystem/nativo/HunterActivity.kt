package com.huntersystem.nativo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

internal val displayFont = FontFamily(Font(R.font.hunter_display))
internal val bodyFont = FontFamily(Font(R.font.hunter_text))
internal val silver = Color(0xffb9d4de)
private val background = Color(0xff10151c)
private data class Exercise(val id: String, val name: String, val category: String, val group: String, val steps: String, val cue: String)

class HunterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val prefs = remember { getSharedPreferences("hunter-profile", MODE_PRIVATE) }
            var saved by remember { mutableStateOf(prefs.getString("profile", null)) }
            var editing by rememberSaveable { mutableStateOf(false) }
            MaterialTheme(colorScheme = darkColorScheme(background = background, primary = silver)) {
                Surface(Modifier.fillMaxSize(), color = background) {
                    if (saved == null || editing) {
                        BackHandler(enabled = editing) { editing = false }
                        Profile(saved) { data ->
                            prefs.edit().putString("profile", data).commit().also { success ->
                                if (success) { saved = data; editing = false }
                            }
                        }
                    } else ArchiveRoute { editing = true }
                }
            }
        }
    }
    @Composable
    private fun ArchiveRoute(editProfile: () -> Unit) {
        var attempt by remember { mutableIntStateOf(0) }
        val catalog by produceState<Result<List<Exercise>>?>(null, attempt) {
            value = null
            value = withContext(Dispatchers.IO) {
                try {
                    val rows = assets.open("ejercicios.json").bufferedReader().use { JSONArray(it.readText()) }
                    Result.success(List(rows.length()) { index ->
                        val row = rows.getJSONObject(index)
                        Exercise(row.getString("id"), row.getString("name"), row.optString("category"),
                            row.optString("group"), row.optString("steps"), row.optString("cue"))
                    })
                } catch (error: java.io.IOException) {
                    Result.failure(error)
                } catch (error: org.json.JSONException) {
                    Result.failure(error)
                }
            }
        }
        val state = catalog
        when {
            state == null -> Awakening()
            state.isSuccess -> ExerciseArchive(state.getOrThrow(), editProfile)
            else -> Column(Modifier.padding(20.dp)) {
                Text("No se pudo abrir el catálogo.")
                Button(onClick = { attempt++ }) { Text("Reintentar") }
                TextButton(onClick = editProfile) { Text("Editar perfil") }
            }
        }
    }

}

@Composable
private fun Awakening() {
    val transition = rememberInfiniteTransition(label = "Despertar")
    val orbit by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(22000, easing = LinearEasing)), label = "Órbita")
    Box(Modifier.fillMaxSize()) {
        Image(painterResource(R.drawable.awakening), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop, alpha = .82f)
        Canvas(Modifier.fillMaxSize()) {
            val diameter = minOf(size.width * .82f, size.height * .65f)
            val center = Offset(size.width / 2, size.height * .48f)
            rotate(orbit, center) {
                drawOval(silver.copy(alpha = .36f), Offset(center.x - diameter / 2, center.y - diameter / 2), Size(diameter, diameter),
                    style = Stroke(1.dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(3.dp.toPx(), 3.dp.toPx()))))
            }
            repeat(28) { i ->
                drawCircle(silver.copy(alpha = .55f), 1.5.dp.toPx(), Offset(((5 + i * 37) % 91) / 100f * size.width, ((5 + i * 23) % 87) / 100f * size.height))
            }
        }
        Column(Modifier.align(Alignment.TopCenter).padding(top = 38.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("HUNTER SYSTEM", fontFamily = displayFont, color = silver, fontSize = 40.sp)
            Text("SISTEMA DE SOMBRAS", fontFamily = bodyFont, letterSpacing = 3.sp)
        }
        Text("Preparando tu entrenamiento…", Modifier.align(Alignment.BottomCenter).padding(28.dp), fontFamily = bodyFont)
    }
}

@Composable
private fun ExerciseArchive(catalog: List<Exercise>, editProfile: () -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("Todas") }
    var selection by rememberSaveable { mutableStateOf<String?>(null) }
    val visible = remember(query, category, catalog) {
        catalog.filter { (category == "Todas" || category == it.category) && (it.name.contains(query, true) || it.group.contains(query, true)) }
    }
    val exercise = remember(selection, catalog) { catalog.find { it.id == selection } }
    BackHandler(enabled = exercise != null) { selection = null }
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Text("HUNTER SYSTEM", Modifier.padding(top = 16.dp), fontSize = 24.sp, fontFamily = displayFont, color = silver)
        Text("SISTEMA DE SOMBRAS", fontSize = 11.sp, letterSpacing = 2.sp, fontFamily = bodyFont)
        TextButton(onClick = editProfile) { Text("Editar perfil") }
        HorizontalDivider(Modifier.padding(vertical = 12.dp))
        if (exercise != null) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                TextButton(onClick = { selection = null }) { Text("← Todos los ejercicios") }
                Text(exercise.group, fontFamily = bodyFont, color = silver)
                Text(exercise.name, fontFamily = displayFont, fontSize = 32.sp)
                Demonstration(exercise.id)
                Text(exercise.steps, Modifier.padding(vertical = 12.dp))
                if (exercise.cue.isNotBlank()) Text(exercise.cue, color = silver)
            }
        } else {
            Text("ARCHIVO DE ENTRENAMIENTO", fontFamily = bodyFont, letterSpacing = 2.sp)
            Text("Todos los ejercicios", fontFamily = displayFont, fontSize = 32.sp)
            OutlinedTextField(query, { query = it }, Modifier.fillMaxWidth(), label = { Text("Buscar ejercicio") }, singleLine = true)
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                for (name in listOf("Todas", "Fuerza", "Cardio", "Movilidad")) {
                    FilterChip(category == name, { category = name }, label = { Text(name, fontFamily = bodyFont) })
                }
            }
            Text("${visible.size} fichas", fontFamily = bodyFont, color = silver)
            LazyColumn {
                items(visible, key = { it.id }) { item ->
                    Column(Modifier.fillMaxWidth().clickable { selection = item.id }.padding(vertical = 10.dp)) {
                        Text(item.name, fontSize = 20.sp, fontFamily = bodyFont)
                        Text("${item.category} · ${item.group}", color = silver, fontFamily = bodyFont)
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}
