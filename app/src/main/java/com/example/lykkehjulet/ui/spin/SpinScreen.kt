package com.example.lykkehjulet.ui.spin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lykkehjulet.StatefulPointsCounter
import com.example.lykkehjulet.data.field.fieldtypes.BankruptcyField
import com.example.lykkehjulet.data.field.fieldtypes.PointsField
import com.example.lykkehjulet.data.local.LocalFieldsDataProvider
import com.example.lykkehjulet.ui.navigation.LykkeHjulFAB
import com.example.lykkehjulet.ui.navigation.LykkeHjulTopBar
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinScreen(
    modifier: Modifier = Modifier,
    spinViewModel: SpinViewModel = viewModel()
) {
    Scaffold(
        topBar = { LykkeHjulTopBar(onClick = {/* TODO */ }) },
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

                    //TODO move out of SpinScreen
                    //selects a random field
                    val randomFieldId = Random.nextLong(LocalFieldsDataProvider.getSize().toLong())
                    val selectedField = LocalFieldsDataProvider.getFieldById(randomFieldId)

                    if(selectedField.type is BankruptcyField) {
                        spinViewModel.resetPoints()
                    } else {
                        val pointsField = selectedField.type as PointsField
                        spinViewModel.setSelectedPoints(pointsField.points)
                        spinViewModel.openPopup()
                    }


                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
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

