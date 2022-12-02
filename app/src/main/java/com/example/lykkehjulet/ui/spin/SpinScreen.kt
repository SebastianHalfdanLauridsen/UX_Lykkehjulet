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
                    val randomFieldId = (0..LocalFieldsDataProvider.getSize()).random().toLong()
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

                if (spinViewModel.openEndDialog.value) {
                    if (spinViewModel.hasLost()) {
                        EndDialog(
                            onDismissRequest = {spinViewModel.closeEndGameDialog()},
                            title = "You lost!",
                            text = "The word was '${spinViewModel.wordToGuess.value.word}! You lost in ${spinViewModel.alreadyGuessedLetters.size} guesses.",
                            onConfirm = {
                                spinViewModel.resetGame()
                                spinViewModel.closeEndGameDialog()
                            }
                        )
                    } else {
                        EndDialog(
                            onDismissRequest = { spinViewModel.closeEndGameDialog() },
                            title = "You won!",
                            text = "You guessed the word '${spinViewModel.wordToGuess.value.word}' in ${spinViewModel.alreadyGuessedLetters.size} guesses.",
                            onConfirm = {
                                spinViewModel.resetGame()
                                spinViewModel.closeEndGameDialog()
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Stateless AlertDialog to announce winner or loser of the game and enables the user to restart the game
 */
@Composable
fun EndDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        icon = {},
        onDismissRequest = onDismissRequest,
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Reset game")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text("Dismiss")
            }
        }
    )
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
