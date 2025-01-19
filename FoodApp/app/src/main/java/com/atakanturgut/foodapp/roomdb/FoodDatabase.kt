package com.atakanturgut.foodapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atakanturgut.foodapp.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDAO
}