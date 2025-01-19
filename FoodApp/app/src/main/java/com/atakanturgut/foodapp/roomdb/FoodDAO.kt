package com.atakanturgut.foodapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.atakanturgut.foodapp.model.Food
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface FoodDAO {
    @Query("select * from Food")
    fun getAll() : Flowable<List<Food>>

    @Query("select * from Food where id = :id")
    fun findById(id : Int) : Flowable<Food>

    @Insert
    fun insert(food: Food) : Completable

    @Delete
    fun delete(food: Food) : Completable
}