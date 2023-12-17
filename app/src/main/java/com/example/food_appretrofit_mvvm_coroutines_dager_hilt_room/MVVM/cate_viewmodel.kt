package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.over
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.reposatory.cate_repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class cate_viewmodel @Inject constructor(
    private val cateRepo: cate_repo
) :ViewModel(){


    private val _getmealtype=MutableLiveData<List<over>>()
    val getmealtype:LiveData<List<over>> = _getmealtype

    fun getmealtype(catename:String){
        viewModelScope.launch {
            try {
                val response=cateRepo.getmealstype(catename)
                if (response.isSuccessful&&response.body()!=null){
                    _getmealtype.value=response.body()!!.meals
                }

            }catch (e:Throwable){
                Log.d("test",e.message.toString())
            }
        }

    }





}