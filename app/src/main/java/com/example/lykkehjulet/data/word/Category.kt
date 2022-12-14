package com.example.lykkehjulet.data.word

/**
 * The different possible word categories
 */
enum class Category {
    DEFAULT {
        override fun toString(): String {
            return "CATEGORY"
        }
    },
    ANIMAL {
        override fun toString(): String {
            return "Animal"
        }
    },
    FOOD {
        override fun toString(): String {
            return "Food"
        }
    },
    TECHNOLOGY {
        override fun toString(): String {
            return "Technology"
        }
    },
    SPORT {
        override fun toString(): String {
            return "Sport"
        }
    }
}