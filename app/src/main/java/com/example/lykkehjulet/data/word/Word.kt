package com.example.lykkehjulet.data.word

/**
 * Data holder for the [word]s to be guessed
 */
data class Word(
    val id: Long,
    val category: Category,
    val word: String
) {
}