package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.reposatory

import android.util.Log
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Api.call_api
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.RoomDB.meal_database
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Ui.meals_details
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.MealList
import retrofit2.Response
import javax.inject.Inject

class Mealdetails_repo @Inject constructor(
    private val mealapi:call_api ,
    private val mealdao:meal_database
) {

    suspend fun getmealdetails(mealid:String):Response<MealList>{
        val response=mealapi.getmymealinformations(mealid)

        if (response.isSuccessful){
            Log.d("test","Succsseful")
            Log.d("test",response.body().toString())

        }
        else{
            Log.d("test","fail")
            Log.d("test",response.body().toString())

        }
        return response

    }

    suspend fun insert(meal:Meal){
        mealdao.connentdao().upsert(meal)

    }

    suspend fun delete(meal:Meal){
        mealdao.connentdao().delete(meal)
    }


    val showalldata=mealdao.connentdao().getallsaveddara()



}