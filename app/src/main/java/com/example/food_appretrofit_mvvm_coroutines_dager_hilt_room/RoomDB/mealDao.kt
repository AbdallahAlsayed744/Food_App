package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal

@Dao
interface mealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal:Meal)

    @Delete
    suspend fun delete(meal: Meal)


    @Query("SELECT * FROM mealsinformation")
    fun getallsaveddara():LiveData<List<Meal>>




}