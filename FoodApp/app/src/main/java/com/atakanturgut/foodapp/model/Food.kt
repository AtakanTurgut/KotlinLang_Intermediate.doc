package com.atakanturgut.foodapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food (
    @ColumnInfo(name = "isim")
    var isim : String,

    @ColumnInfo(name = "malzeme")
    var malzeme : String,

    @ColumnInfo(name = "tarif")
    var tarif : String?,

    @ColumnInfo(name = "gorsel")
    var gorsel : ByteArray
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}