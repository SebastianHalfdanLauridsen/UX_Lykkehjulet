package com.example.lykkehjulet.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.lykkehjulet.R

@Composable
fun ResetGameIcon() {
    Icon(
        imageVector = Icons.Rounded.Refresh,
        contentDescription = "Reset game"
    )
}

@Composable
fun SpinWheelIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_round_restart_alt_24),
        contentDescription = "Spin wheel",
        modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
    )
}