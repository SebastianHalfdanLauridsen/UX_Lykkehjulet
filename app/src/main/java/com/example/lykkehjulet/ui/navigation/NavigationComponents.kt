package com.example.lykkehjulet.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.lykkehjulet.util.ResetGameIcon
import com.example.lykkehjulet.util.SpinWheelIcon

/**
 * Stateless top bar with button
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LykkeHjulTopBar(onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "LykkehjuletTitle")
        },
        actions = {
            Button(
                onClick = onClick
            ) {
                ResetGameIcon()
            }
        }
    )
}

/**
 * Stateless FAB
 */
@Composable
fun LykkeHjulFAB(onClick: () -> Unit) {
    LargeFloatingActionButton(
        onClick = onClick
    ) {
        SpinWheelIcon()
    }
}