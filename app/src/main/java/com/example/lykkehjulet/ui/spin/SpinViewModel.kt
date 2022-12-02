package com.example.lykkehjulet.ui.spin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lykkehjulet.data.local.LocalWordsDataProvider
import com.example.lykkehjulet.data.word.Category
import com.example.lykkehjulet.data.word.Word

/**
 * The ViewModel ensures that we save our data outside of composition
 * changes and that we modify data in a controlled way.
 */
class SpinViewModel : ViewModel() {
    private val startingLives = 5
    private val hiddenChar = '*'

    private val _points = mutableStateOf(0)
    val points: MutableState<Int>
        get() = _points

    private val _lives = mutableStateOf(5)
    val lives: MutableState<Int>
        get() = _lives

    private val _guessedWord = mutableStateOf("")
    val guessedWord: MutableState<String>
        get() = _guessedWord

    private val _wordToGuess =
        mutableStateOf(
            Word(
                id = -1L,
                category = Category.DEFAULT,
                word = ""
            )
        )
    val wordToGuess: MutableState<Word>
        get() = _wordToGuess

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

    private val _openEndDialog = mutableStateOf(false)
    val openEndDialog: MutableState<Boolean>
        get() = _openEndDialog


    //only these functions can modify the lives and points
    fun decreaseLives() {
        _lives.value--
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
        return _wordToGuess.value.word.contains(letter, ignoreCase = true)
    }

    fun revealLetter(guessedLetter: Char) {
        _wordToGuess.value.word.withIndex().forEach { (i, letter) ->
            if (letter.uppercaseChar() == guessedLetter.uppercaseChar()) {
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
        //counts number of letters were correct
        val multiplier = _wordToGuess.value.word.count {it == _guessedLetter.value.toCharArray()[0]}
        _points.value += (amount*multiplier)
    }

    fun isWordGuessed(): Boolean {
        return _wordToGuess.value.word == _guessedWord.value
    }

    fun setIsPopupScreenButtonEnabled(isEnabled: Boolean) {
        _isPopupScreenButtonEnabled.value = isEnabled
    }

    fun resetGuessedLetter() {
        _guessedLetter.value = ""
    }

    fun openEndGameDialog() {
        _openEndDialog.value = true
    }

    fun closeEndGameDialog() {
        _openEndDialog.value = false
    }

    fun hasLost(): Boolean {
        return _lives.value == 0
    }

    fun resetGame() {
        resetPoints()
        resetLives()
        resetAlreadyGuessedLetters()
        fetchNewWord()
    }

    private fun resetLives() {
        _lives.value = startingLives
    }

    fun resetPoints() {
        _points.value = 0
    }

    fun resetAlreadyGuessedLetters() {
        _alreadyGuessedLetters.clear()
    }

    fun fetchNewWord() {
        _wordToGuess.value = getNewWord()
        resetGuessedWord()
    }

    /**
     * Returns a new word from the [LocalWordsDataProvider]
     */
    private fun getNewWord(): Word {
        return LocalWordsDataProvider.getWordById(
            (0..LocalWordsDataProvider.getSize()).random().toLong()
        )
    }

    private fun resetGuessedWord() {
        _guessedWord.value = ""
        println("GW: ${_guessedWord.value}")
        println("WTG: ${_wordToGuess.value.word}")

        for ((i, _) in _wordToGuess.value.word.withIndex()) {
            _guessedWord.value = (_guessedWord.value + hiddenChar)
        }
        println("GW: ${_guessedWord.value}")
        println("WTG: ${_wordToGuess.value.word}")
    }

    fun getGuessedWordCategory(): String {
        return _wordToGuess.value.category.toString()
    }

}