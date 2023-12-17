package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Api

import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Ui.meals_details
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.MealList
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.categorylist
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.overlist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface call_api {
    @GET("random.php")
    suspend fun getrandommeal():Response<MealList>

    @GET("filter.php")
    suspend fun getovermeals(
        @Query("c") foodname:String
    ):Response<overlist>

    @GET("categories.php")
    suspend fun getcategorylist():Response<categorylist>

    @GET("lookup.php")
    suspend fun getmymealinformations(@Query("i") meal_id:String):Response<MealList>

    @GET("filter.php")
    suspend fun getmealstype(@Query("c") foodname:String):Response<overlist>

}