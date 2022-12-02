package com.example.lykkehjulet.data.field.fieldtypes

import com.example.lykkehjulet.data.field.FieldType

/**
 * Points field with an amount of [points]
 */
data class PointsField(
    val points: Int
) : FieldType