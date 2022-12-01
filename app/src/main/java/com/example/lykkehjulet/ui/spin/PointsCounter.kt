package com.example.lykkehjulet

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun StatefulPointsCounter(
    points: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    StatelessPointsCounter(
        text = "Points",
        count = points,
        onChange = { /*TODO change amount of points when spinning wheel */ },
        modifier = modifier
    )
}

/**
 * Reusable counter for tracking points and lives.
 */
@Composable
fun StatelessPointsCounter(
    text: String,
    count: MutableState<Int>,
    onChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(text = "$text: ${count.value}")
}