package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Category
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.over
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.reposatory.Home_repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: Home_repo
):ViewModel() {
    private val _getrandomeal=MutableLiveData<Meal>()
    val getrandomeal:LiveData<Meal> = _getrandomeal

    fun getrandommeal(){
        viewModelScope.launch {
            try {
                val response = homeRepo.getrandommeal()
                if (response.isSuccessful && response.body() != null) {
                    _getrandomeal.value = response.body()!!.meals[0]
                }

            }catch (e:Throwable){
                Log.d("random meal error",e.message.toString())
            }

        }
    }

    private val _getovermeals=MutableLiveData<List<over>>()
    val getovermeals:LiveData<List<over>> = _getovermeals

    fun getovermeals(){
        viewModelScope.launch {
            try {
                val response=homeRepo.getovermeal("Seafood")
                if (response.isSuccessful&& response.body() != null){
                    _getovermeals.postValue(response.body()!!.meals)
                }

            }catch (e:Throwable){
                Log.d("over meals error",e.message.toString())
            }

        }

    }
    private val _getcategorylist =MutableLiveData<List<Category>>()
    val getcategorylist:LiveData<List<Category>> =_getcategorylist

    fun getcategorymeals(){
        viewModelScope.launch {
            try {
                val response=homeRepo.getcategorylist()
                if (response.isSuccessful&&response.body()!=null){
                    _getcategorylist.postValue(response.body()!!.categories)
                }

            }
            catch (e:Throwable){
                Log.d("category meals error",e.message.toString())

            }

        }

    }

}