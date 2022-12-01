package com.example.lykkehjulet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lykkehjulet.ui.navigation.LykkeHjulFAB
import com.example.lykkehjulet.ui.navigation.LykkeHjulTopBar
import com.example.lykkehjulet.ui.spin.PopupSpinScreen
import com.example.lykkehjulet.ui.spin.SpinViewModel
import com.example.lykkehjulet.ui.spin.StatefulLivesCounter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinScreen(
    modifier: Modifier = Modifier,
    spinViewModel: SpinViewModel = viewModel()
) {
    Scaffold(
        topBar = { LykkeHjulTopBar() },
        floatingActionButton = {
            //TODO make game?
            LykkeHjulFAB(
                onClick = {
                    //TODO
                    // disable ability to spin
                    // get random amount of points
                    // let user guess letter
                    // check if letter is correct
                    // alert user of correctness
                    // show letter if correct
                    // enable spinning again

                    spinViewModel.selectedPoints((Math.random()*100).toInt())
                    spinViewModel.openPopup()
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    StatefulPointsCounter(points = spinViewModel.points)
                    Spacer(modifier = Modifier.weight(1f))
                    StatefulLivesCounter(lives = spinViewModel.lives)
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = spinViewModel.guessedWord.value)



                if (spinViewModel.controlPopup.value) {
                    PopupSpinScreen()
                }
            }

        }
    }

}

