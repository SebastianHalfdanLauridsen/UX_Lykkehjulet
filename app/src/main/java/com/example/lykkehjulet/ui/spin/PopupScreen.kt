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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupSpinScreen(spinViewModel: SpinViewModel = viewModel()) {

    val points = spinViewModel.points.value
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
        ElevatedCard(modifier = Modifier.size(width = 378.dp, height = 511.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {


                Text(text = "Spinning wheel!", fontSize = 20.sp)
                Spacer(modifier = Modifier.size(5.dp))

                Divider()

                Text(text = "...")
                Spacer(modifier = Modifier.weight(1F))
                Text(text = "You spun the wheel and got $points point. You may now guess a letter!")
                Spacer(modifier = Modifier.weight(2F))

                var guessedLetter by remember { mutableStateOf("") }
                var isButtonEnabled by remember { mutableStateOf(false) }

                OutlinedTextField(
                    value = guessedLetter,
                    onValueChange = {
                        guessedLetter = it
                        isButtonEnabled = //TODO check if letter has been guessed already
                            !(guessedLetter.isEmpty() || guessedLetter.length > 1 || !(guessedLetter.all { it.isLetter() }))
                    },
                    label = { Text(text = "Guessed letter") },
                    singleLine = true,
                    isError = (guessedLetter.isEmpty() || guessedLetter.length > 1 || (guessedLetter.all { !it.isLetter() })),
                    supportingText = { Text("Only 1 letter required") /*TODO rework or reword*/ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )

                Spacer(modifier = Modifier.weight(1f))

                FilledTonalButton(
                    onClick = {

                        //TODO determine if the letter was correct!
                        if (spinViewModel.isLetterCorrect(guessedLetter)) {
                            spinViewModel.revealLetter(guessedLetter.toCharArray()[0])
                        }
                        spinViewModel.closePopup()
                    },
                    enabled = isButtonEnabled,
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    Text(text = "Guess letter")
                }
            }
        }

    }
}