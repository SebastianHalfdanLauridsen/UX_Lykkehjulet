package com.example.lykkehjulet.data.word

enum class Category {
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