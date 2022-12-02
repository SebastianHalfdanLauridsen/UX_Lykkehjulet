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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinScreen(spinViewModel: SpinViewModel = viewModel()) {
    Scaffold(
        topBar = {
            LykkeHjulTopBar(
                onClick = {
                    spinViewModel.resetGame()
                }
            )
        },
        floatingActionButton = {
            LykkeHjulFAB(
                onClick = {
                    //TODO change FAB to normal button as FAB cannot be disabled
                    //selects a random field
                    val randomFieldId =
                        (0 until LocalFieldsDataProvider.getSize()).random().toLong()
                    val selectedField = LocalFieldsDataProvider.getFieldById(randomFieldId)

                    if (selectedField.type is BankruptcyField) {
                        spinViewModel.resetPoints()
                        spinViewModel.openBankruptcyDialog()
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

                if (spinViewModel.guessedWord.value.isEmpty()) {
                    spinViewModel.fetchNewWord()
                }

                Word(spinViewModel.getGuessedWordCategory(), spinViewModel.guessedWord.value)


                if (spinViewModel.bankruptcyDialog.value) {
                    AlertDialog(
                        onDismissRequest = { spinViewModel.closeBankruptcyDialog() },
                        title = { Text(text = "Bankruptcy!") },
                        text = { Text(text = "You landed on the bankruptcy field and lost all your money.") },
                        confirmButton = {
                            TextButton(
                                onClick = { spinViewModel.closeBankruptcyDialog() })
                            {
                                Text(text = "Okay")
                            }
                        }
                    )
                } else if (spinViewModel.controlPopup.value) {
                    SpinPopupScreen()
                }

                if (spinViewModel.openEndDialog.value) {
                    if (spinViewModel.hasLost()) {
                        EndDialog(
                            onDismissRequest = { spinViewModel.closeEndGameDialog() },
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
 * Stateless function which displays [word] as the word to guess and its [category].
 */
@Composable
fun Word(category: String, word: String) {
    Text(
        text = category,
        modifier = Modifier.padding(16.dp)
    )
    OutlinedCard {
        //TODO use monospaced font to avoid Card resizing when guessing a correct letter
        Text(
            text = word,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
