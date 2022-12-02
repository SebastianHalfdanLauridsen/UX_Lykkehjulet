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

    /**
     * Initial word as setup
     */
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

    private val _bankruptcyDialog = mutableStateOf(false)
    val bankruptcyDialog: MutableState<Boolean>
        get() = _bankruptcyDialog


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

    fun changeGuessedLetters(letter: String) {
        _guessedLetter.value = letter
    }

    /**
     * Determines if the [letter] is in the [wordToGuess]
     */
    fun isLetterCorrect(letter: Char): Boolean {
        return _wordToGuess.value.word.contains(letter, ignoreCase = true)
    }

    /**
     * Replaces the [hiddenChar] with the [guessedLetter] in all correct places
     */
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

    /**
     * Determines if the [letter] is in the [alreadyGuessedLetters]
     */
    fun isLetterGuessed(letter: Char): Boolean {
        return letter.uppercaseChar() in _alreadyGuessedLetters
    }

    /**
     * Appends the [guessedLetter] to the list of [alreadyGuessedLetters]
     */
    fun saveGuessedLetter(guessedLetter: Char) {
        _alreadyGuessedLetters.add(guessedLetter.uppercaseChar())
    }

    fun setSelectedPoints(points: Int) {
        _selectedPoints.value = points
    }

    /**
     * Adds an [amount] of points multiplied by the number of instances of the guessed letter
     */
    fun addPoints(amount: Int) {
        //counts the number of correct letters
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
        clearAlreadyGuessedLetters()
        fetchNewWord()
    }

    private fun resetLives() {
        _lives.value = startingLives
    }

    fun resetPoints() {
        _points.value = 0
    }

    private fun clearAlreadyGuessedLetters() {
        _alreadyGuessedLetters.clear()
    }

    /**
     * Retrieves a new word and resets the guessed word
     */
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

    /**
     * Resets the guessed word by making it match the [wordToGuess] with [hiddenChar]s
     */
    private fun resetGuessedWord() {
        _guessedWord.value = ""

        for ((i, _) in _wordToGuess.value.word.withIndex()) {
            _guessedWord.value = (_guessedWord.value + hiddenChar)
        }
        println("Word to guess: ${_wordToGuess.value.word}")
    }

    fun getGuessedWordCategory(): String {
        return _wordToGuess.value.category.toString()
    }

    fun openBankruptcyDialog() {
        _bankruptcyDialog.value = true
    }

    fun closeBankruptcyDialog() {
        _bankruptcyDialog.value = false
    }

}