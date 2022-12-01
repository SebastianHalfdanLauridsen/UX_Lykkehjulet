package com.example.lykkehjulet.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SpinWheelIcon() {
    Icon(
        imageVector = Icons.Rounded.Refresh,
        contentDescription = "Spin Wheel",
        modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
    )
}