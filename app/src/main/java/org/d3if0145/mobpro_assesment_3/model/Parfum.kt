package org.d3if0145.mobpro_assesment_3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parfum")
data class Parfum(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val namaParfum: String,
    val brandParfum: String,
    val gender: String,
    val image: String,
    val email: String
)
