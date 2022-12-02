package com.example.lykkehjulet.ui.spin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lykkehjulet.data.field.fieldtypes.BankruptcyField
import com.example.lykkehjulet.data.field.fieldtypes.PointsField
import com.example.lykkehjulet.data.local.LocalFieldsDataProvider
import com.example.lykkehjulet.ui.navigation.LykkeHjulFAB
import com.example.lykkehjulet.ui.navigation.LykkeHjulTopBar
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinScreen(spinViewModel: SpinViewModel = viewModel()) {
    Scaffold(
        topBar = { LykkeHjulTopBar(onClick = {/* TODO */ }) },
        floatingActionButton = {
            LykkeHjulFAB(
                onClick = {
                    //selects a random field
                    val randomFieldId = Random.nextLong(LocalFieldsDataProvider.getSize().toLong())
                    val selectedField = LocalFieldsDataProvider.getFieldById(randomFieldId)

                    if (selectedField.type is BankruptcyField) {
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
                    Counter(precedingText = "Points", count = spinViewModel.points)
                    Spacer(modifier = Modifier.weight(1f))
                    Counter(precedingText = "Lives", count = spinViewModel.lives)
                }

                Spacer(modifier = Modifier.padding(16.dp))

                Word(spinViewModel.guessedWord.value)

                if (spinViewModel.controlPopup.value) {
                    SpinPopupScreen()
                }
            }

        }
    }

}

/**
 * Stateless counter with [count] as the number to be displayed.
 */
@Composable
fun Counter(
    precedingText: String,
    count: MutableState<Int>,
) {
    Card {
        Text(
            text = "$precedingText: ${count.value}",
            modifier = Modifier.padding(16.dp),
        )
    }
}

/**
 * Stateless display [text] as the word to guess.
 */
@Composable
fun Word(text: String) {
    OutlinedCard {
        //TODO use monospaced font to avoid Card resizing when guessing a correct letter
        Text(
            text = text,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
