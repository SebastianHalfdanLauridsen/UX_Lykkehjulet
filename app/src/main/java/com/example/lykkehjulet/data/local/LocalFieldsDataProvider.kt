package com.example.lykkehjulet.data.local

import androidx.compose.ui.graphics.Color
import com.example.lykkehjulet.data.field.Field
import com.example.lykkehjulet.data.field.fieldtypes.BankruptcyField
import com.example.lykkehjulet.data.field.fieldtypes.PointsField

/**
 * Static data store of [Field]s.
 */
object LocalFieldsDataProvider {

    val allWheelFields = listOf(
        Field(
            id = 1L,
            type = BankruptcyField(),
            color = Color(0xFF010101)
        ),
        Field(
            id = 2L,
            type = PointsField(points = 1500),
            color = Color(0xFF7F8000)
        ),
        Field(
            id = 3L,
            type = PointsField(points = 800),
            color = Color(0xFFF96400)
        ),
        Field(
            id = 4L,
            type = PointsField(points = 100),
            color = Color(0xFF007FFF)
        ),
        Field(
            id = 5L,
            type = PointsField(points = 500),
            color = Color(0xFF00964C)
        ),
        Field(
            id = 6L,
            type = PointsField(points = 600),
            color = Color(0xFFFF7F00)
        ),
        Field(
            id = 7L,
            type = PointsField(points = 500),
            color = Color(0xFFFFFF00)
        ),
        Field( // JOKER 0xFF81007F
            id = 8L,
            type = PointsField(points = 300),
            color = Color(0xFF00964C)
        ),
        Field(
            id = 9L,
            type = PointsField(points = 800),
            color = Color(0xFF007FFF)
        ),
        Field(
            id = 10L,
            type = PointsField(points = 500),
            color = Color(0xFFFFFF00)
        ),
        Field(
            id = 11L,
            type = PointsField(points = 800),
            color = Color(0xFFFE0000)
        ),
        Field(
            id = 12L,
            type = PointsField(points = 800),
            color = Color(0xFFF96400)
        ),
        Field(
            id = 13L,
            type = PointsField(points = 100),
            color = Color(0xFF00964C)
        ),
        Field(
            id = 14L,
            type = PointsField(points = 300),
            color = Color(0xFFFE0000)
        ),
        Field(
            id = 15L,
            type = PointsField(points = 800),
            color = Color(0xFF007FFF)
        ),
        Field(
            id = 16L,
            type = PointsField(points = 1000),
            color = Color(0xFFFE0000)
        ),
        Field(
            id = 17L,
            type = PointsField(points = 500),
            color = Color(0xFFFFFF00)
        ),
        Field( // TUR 0xFF81007F
            id = 18L,
            type = PointsField(points = 300),
            color = Color(0xFF007FFF)
        ),
        Field(
            id = 19L,
            type = PointsField(points = 600),
            color = Color(0xFFF96400)
        ),
        Field(
            id = 20L,
            type = PointsField(points = 500),
            color = Color(0xFF00964C)
        ),
        Field(
            id = 21L,
            type = PointsField(points = 800),
            color = Color(0xFFFE0000)
        ),
        Field(
            id = 22L,
            type = PointsField(points = 500),
            color = Color(0xFFFFFF00)
        )
    )

    fun getFieldById(id: Long): Field {
        return allWheelFields.first {it.id == id}
    }
}