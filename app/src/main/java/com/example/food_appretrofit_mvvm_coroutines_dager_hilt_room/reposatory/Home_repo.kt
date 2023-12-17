package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.reposatory

import android.util.Log
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Api.call_api
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.MealList
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.categorylist
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.overlist
import retrofit2.Response
import javax.inject.Inject

class Home_repo @Inject constructor(
    private val meal_api:call_api
) {
    suspend fun getrandommeal():Response<MealList>{
        val response=meal_api.getrandommeal()
        if (response.isSuccessful){
            Log.d("test1","success")
            Log.d("test1",response.body().toString())
        }
        else{
            Log.d("test1","fail")
            Log.d("test1",response.body().toString())
        }
        return response
    }

    suspend fun getovermeal(foodname:String):Response<overlist>{
        val response=meal_api.getovermeals(foodname)
        if (response.isSuccessful){
            Log.d("test1","success")
            Log.d("test1",response.body().toString())
        }
        else{
            Log.d("test1","fail")
            Log.d("test1",response.body().toString())
        }
        return response
    }

    suspend fun getcategorylist():Response<categorylist>{
        val response=meal_api.getcategorylist()
        if (response.isSuccessful){
            Log.d("test1","success")
            Log.d("test1",response.body().toString())
        }
        else{
            Log.d("test1","fail")
            Log.d("test1",response.body().toString())
        }
        return response

    }




}