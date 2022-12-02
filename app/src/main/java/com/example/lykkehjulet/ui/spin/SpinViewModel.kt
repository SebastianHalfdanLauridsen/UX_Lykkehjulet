package com.example.lykkehjulet.ui.spin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * The ViewModel ensures that we save our data outside of composition
 * changes and that we modify data in a controlled way.
 */
class SpinViewModel : ViewModel() {
    private val startingLives = 5

    private val _points = mutableStateOf(0)
    val points: MutableState<Int>
        get() = _points

    private val _lives = mutableStateOf(5)
    val lives: MutableState<Int>
        get() = _lives

    private val _wordToGuess = mutableStateOf("JEFF")
    val wordToGuess: MutableState<String>
        get() = _wordToGuess

    private val _guessedWord = mutableStateOf("****")
    val guessedWord: MutableState<String>
        get() = _guessedWord

    private val _controlPopup = mutableStateOf(false)
    val controlPopup: MutableState<Boolean>
        get() = _controlPopup

    private val _guessedLetter = mutableStateOf("")
    val guessedLetter: MutableState<String>
        get() = _guessedLetter

    private val _alreadyGuessedLetters = mutableListOf<Char>()
    val alreadyGuessedLetters: MutableList<Char>
        get() = _alreadyGuessedLetters

    private val _selectedPoints = mutableStateOf(0)
    val selectedPoints: MutableState<Int>
        get() = _selectedPoints

    private val _isPopupScreenButtonEnabled = mutableStateOf(false)
    val isPopupScreenButtonEnabled: MutableState<Boolean>
        get() = _isPopupScreenButtonEnabled

    //only these functions can modify the lives and points
    fun decreaseLives() {
        _lives.value--
    }

    fun resetLives() {
        _lives.value = startingLives
    }

    fun closePopup() {
        _controlPopup.value = false
    }

    fun openPopup() {
        _controlPopup.value = true
    }

    fun guessedLetterChange(letter: String) {
        _guessedLetter.value = letter
    }

    fun isLetterCorrect(letter: Char): Boolean {
        return _wordToGuess.value.contains(letter, ignoreCase = true)
    }

    fun revealLetter(guessedLetter: Char) {
        _wordToGuess.value.withIndex().forEach { (i, letter) ->
            if (letter == guessedLetter.uppercaseChar()) {
                // inspired by https://www.techiedelight.com/replace-character-specific-index-string-kotlin/
                //replaces index with letter
                _guessedWord.value =
                    StringBuilder(_guessedWord.value).also { it.setCharAt(i, letter) }.toString()
            }
        }
    }

    fun isLetterGuessed(letter: Char): Boolean {
        return letter.uppercaseChar() in _alreadyGuessedLetters
    }

    fun saveGuessedLetter(guessedLetter: Char) {
        _alreadyGuessedLetters.add(guessedLetter.uppercaseChar())
    }

    fun setSelectedPoints(points: Int) {
        _selectedPoints.value = points
    }

    fun addPoints(amount: Int) {
        //TODO check if there are multiple letters
        _points.value += amount
    }

    fun resetPoints() {
        _points.value = 0
    }

    fun isWordGuessed(): Boolean {
        return _wordToGuess.value == _guessedWord.value
    }

    fun setIsPopupScreenButtonEnabled(isEnabled: Boolean) {
        _isPopupScreenButtonEnabled.value = isEnabled
    }

    fun resetGuessedLetter() {
        _guessedLetter.value = ""
    }
}