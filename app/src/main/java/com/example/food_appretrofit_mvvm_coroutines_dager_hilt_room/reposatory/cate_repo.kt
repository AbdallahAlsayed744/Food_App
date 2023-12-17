package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.reposatory

import android.util.Log
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Api.call_api
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.overlist
import retrofit2.Response
import javax.inject.Inject

class cate_repo @Inject constructor(
    private val mealapi:call_api
) {
    suspend fun getmealstype(categoryname:String):Response<overlist>{
        val response=mealapi.getmealstype(categoryname)
        if (response.isSuccessful){
            Log.d("test","succsseful")
            Log.d("test",response.body().toString())
        }
        else{
            Log.d("test","succsseful")
            Log.d("test",response.body().toString())

        }
        return response
    }




}