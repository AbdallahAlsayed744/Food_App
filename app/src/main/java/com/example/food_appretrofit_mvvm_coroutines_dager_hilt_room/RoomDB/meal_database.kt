package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.RoomDB

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(typeconverter::class)
abstract class meal_database : RoomDatabase() {
    abstract fun connentdao():mealDao



}