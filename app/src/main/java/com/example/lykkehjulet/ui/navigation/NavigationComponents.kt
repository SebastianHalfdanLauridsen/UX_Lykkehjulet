package com.example.lykkehjulet.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.lykkehjulet.util.SpinWheelIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LykkeHjulTopBar() {
    TopAppBar(
        title = {
            Text(text = "LykkehjuletTitle")
        },
        actions = {
            AssistChip(
                onClick = { /* TODO points.value = 0 */ },
                label = { Text(text = "new game") }
            )
        }
    )
}

@Composable
fun LykkeHjulFAB(onClick: () -> Unit) {
    LargeFloatingActionButton(
        onClick =  onClick
    ) {
        SpinWheelIcon()
    }
}