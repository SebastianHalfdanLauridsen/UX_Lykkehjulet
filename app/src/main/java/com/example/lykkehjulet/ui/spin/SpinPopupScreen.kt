package com.example.lykkehjulet.ui.spin


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SpinPopupScreen(spinViewModel: SpinViewModel = viewModel()) {

    // Inspired from https://medium.com/mobile-app-development-publication/jetpack-compose-popup-master-it-98accb23da36
    Popup(
        alignment = Alignment.Center,
        offset = IntOffset(0, 1200),
        onDismissRequest = { /*TODO popupControl = false*/ },
        properties = PopupProperties(
            focusable = true,
            //dismissOnBackPress = false,
            //dismissOnClickOutside = false
        )
    ) {
        ElevatedCard(modifier = Modifier.size(width = 378.dp, height = 480.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Spinning wheel!", fontSize = 20.sp)
                Spacer(modifier = Modifier.size(5.dp))

                Divider()

                Text(text = "...")
                Spacer(modifier = Modifier.weight(1F))
                Text(text = "You spun the wheel and got ${spinViewModel.selectedPoints.value} points. You may now guess a letter!")
                Spacer(modifier = Modifier.weight(2F))

                GetLetterInput(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    spinViewModel = spinViewModel,
                )

                Spacer(modifier = Modifier.weight(1f))

                FilledTonalButton(
                    onClick = {
                        val guessedLetter = spinViewModel.guessedLetter.value.toCharArray()[0]

                        if (spinViewModel.isLetterCorrect(guessedLetter)) {
                            spinViewModel.revealLetter(guessedLetter)
                            spinViewModel.addPoints(spinViewModel.selectedPoints.value)

                            println("Word comp: ${spinViewModel.guessedWord.value} | ${spinViewModel.wordToGuess.value}")

                            if (spinViewModel.isWordGuessed()) {
                                //TODO announce win and restart game
                                println("WINNER")
                            }

                        } else {
                            spinViewModel.decreaseLives()

                            if (spinViewModel.lives.value == 0) {
                                //TODO announce defeat and restart game
                                println("LOSER")
                            }
                        }
                        spinViewModel.saveGuessedLetter(guessedLetter)
                        spinViewModel.resetGuessedLetter()
                        spinViewModel.closePopup()
                    },
                    enabled = spinViewModel.isPopupScreenButtonEnabled.value,
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    Text(text = "Guess letter")
                }
            }
        }
    }
}

/**
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetLetterInput(modifier: Modifier = Modifier, spinViewModel: SpinViewModel = viewModel()) {
    var guessedLetterIsChar by remember { mutableStateOf(false) }
    var guessedLetterIsLetter by remember { mutableStateOf(false) }
    var guessedLetterIsAlreadyGuessed by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = spinViewModel.guessedLetter.value,
        onValueChange = {
            spinViewModel.guessedLetterChange(it)

            guessedLetterIsChar = spinViewModel.guessedLetter.value.length == 1
            guessedLetterIsLetter = spinViewModel.guessedLetter.value.all { it.isLetter() }
            if (spinViewModel.guessedLetter.value.isNotEmpty()) {
                guessedLetterIsAlreadyGuessed =
                    spinViewModel.isLetterGuessed(spinViewModel.guessedLetter.value.toCharArray()[0])
            }

            spinViewModel.setIsPopupScreenButtonEnabled(
                guessedLetterIsChar && guessedLetterIsLetter
                        && !guessedLetterIsAlreadyGuessed
            )
        },
        label = { Text(text = "Guessed letter") },
        singleLine = true,
        isError = !guessedLetterIsChar
                || !guessedLetterIsLetter || guessedLetterIsAlreadyGuessed,
        supportingText = {
            if (!guessedLetterIsChar) {
                Text(text = "Enter only a single character")
            } else if (!guessedLetterIsLetter) {
                Text(text = "Character is not a letter")
            } else if (guessedLetterIsAlreadyGuessed) {
                Text("Letter is already guessed")
            } else {
                Text(text = "Enter a single character")
            }
        },
        modifier = modifier
    )
}
