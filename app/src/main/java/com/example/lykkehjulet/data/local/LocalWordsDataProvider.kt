package com.example.lykkehjulet.data.local

import com.example.lykkehjulet.data.word.Category
import com.example.lykkehjulet.data.word.Word

object LocalWordsDataProvider {

    val allWords = listOf(
        Word(
            id = 1L,
            category = Category.ANIMAL,
            word = "Lion"
        ),
        Word(
            id = 2L,
            category = Category.ANIMAL,
            word = "Pigeon"
        ),
        Word(
            id = 3L,
            category = Category.ANIMAL,
            word = "Snail"
        ),
        Word(
            id = 4L,
            category = Category.FOOD,
            word = "Lasagne"
        ),
        Word(
            id = 5L,
            category = Category.FOOD,
            word = "Roast pork brown gravy and potatoes"
        ),
        Word(
            id = 6L,
            category = Category.FOOD,
            word = "Sausage"
        ),
        Word(
            id = 7L,
            category = Category.SPORT,
            word = "Badminton"
        ),
        Word(
            id = 8L,
            category = Category.SPORT,
            word = "Golf"
        ),
        Word(
            id = 9L,
            category = Category.SPORT,
            word = "Paddle-ton"
        ),
        Word(
            id = 10L,
            category = Category.TECHNOLOGY,
            word = "Television"
        ),
        Word(
            id = 11L,
            category = Category.TECHNOLOGY,
            word = "Central processing unit"
        ),
        Word(
            id = 12L,
            category = Category.TECHNOLOGY,
            word = "Electricity"
        ),
    )

    fun getWordById(id: Long): Word {
        return allWords.first { it.id == id }
    }
}