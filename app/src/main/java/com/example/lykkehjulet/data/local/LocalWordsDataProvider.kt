package com.example.lykkehjulet.data.local

import com.example.lykkehjulet.data.word.Category
import com.example.lykkehjulet.data.word.Word

/**
 * Static data store of [Word]s
 */
object LocalWordsDataProvider {

    private val allWords = listOf(
        Word(
            id = 0L,
            category = Category.ANIMAL,
            word = "Lion"
        ),
        Word(
            id = 1L,
            category = Category.ANIMAL,
            word = "Pigeon"
        ),
        Word(
            id = 2L,
            category = Category.ANIMAL,
            word = "Snail"
        ),
        Word(
            id = 3L,
            category = Category.FOOD,
            word = "Lasagne"
        ),
        Word(
            id = 4L,
            category = Category.FOOD,
            word = "Spaghetti"
        ),
        Word(
            id = 5L,
            category = Category.FOOD,
            word = "Sausage"
        ),
        Word(
            id = 6L,
            category = Category.SPORT,
            word = "Badminton"
        ),
        Word(
            id = 7L,
            category = Category.SPORT,
            word = "Golf"
        ),
        Word(
            id = 8L,
            category = Category.SPORT,
            word = "Cricket"
        ),
        Word(
            id = 9L,
            category = Category.TECHNOLOGY,
            word = "Television"
        ),
        Word(
            id = 10L,
            category = Category.TECHNOLOGY,
            word = "Motherboard"
        ),
        Word(
            id = 11L,
            category = Category.TECHNOLOGY,
            word = "Electricity"
        ),
    )

    fun getWordById(id: Long): Word {
        return allWords.first { it.id == id }
    }

    fun getSize(): Int {
        return allWords.size
    }
}