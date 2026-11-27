package com.huntersystem.nativo

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33], qualifiers = "w393dp-h852dp")
class StartupTest {
    @get:Rule val app = createAndroidComposeRule<HunterActivity>()

    @Test fun firstLaunchSaveAndReopen() {
        app.mainClock.autoAdvance = false
        app.onNodeWithText("Preparando tu entrenamiento…").assertExists()
        app.onNodeWithText("Reintentar").assertDoesNotExist()
        app.onNodeWithText("Crea tu perfil").assertDoesNotExist()
        app.mainClock.advanceTimeBy(1700)
        app.mainClock.autoAdvance = true
        app.waitUntil(10000) { app.onAllNodesWithText("Crea tu perfil").fetchSemanticsNodes().isNotEmpty() }
        app.onNodeWithText("Preparando tu entrenamiento…").assertDoesNotExist()
        app.onNodeWithText("Reintentar").assertDoesNotExist()
        val fields = app.onAllNodes(hasSetTextAction())
        listOf("Prueba", "20", "170", "65").forEachIndexed { index, value ->
            fields[index].performScrollTo().performTextInput(value)
        }
        app.onNodeWithText("DESPERTAR MI SISTEMA →").performScrollTo().performClick()
        awaitArchive()
        app.activityRule.scenario.recreate()
        awaitArchive()
        app.onNodeWithText("Reintentar").assertDoesNotExist()
        app.onNodeWithText("Editar perfil").performClick()
        app.onNodeWithText("Prueba").assertExists()
    }

    private fun awaitArchive() {
        app.waitUntil(10000) { app.onAllNodesWithText("Todos los ejercicios").fetchSemanticsNodes().isNotEmpty() }
    }
}
