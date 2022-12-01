package com.example.lykkehjulet.ui.spin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun StatefulLivesCounter(
    lives: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    StatelessLivesCounter(
        text = "Lives",
        count = lives,
        onChange = { /*TODO decrease amount of lives when guessing the incorrect letter*/ },
        modifier = modifier
    )
}

/**
 * Reusable counter for tracking points and lives.
 */
@Composable
fun StatelessLivesCounter(
    text: String,
    count: MutableState<Int>,
    onChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(text = "$text: ${count.value}")
}