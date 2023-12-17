package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.reposatory.Mealdetails_repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class mealdetails_viewmodel @Inject constructor(
    private val mealdetailsRepo: Mealdetails_repo
):ViewModel() {
    private val _getmeailsdetails= MutableLiveData<Meal>()
    val getmeailsdetails:LiveData<Meal> =_getmeailsdetails

    fun getmeailsdetails(mealid:String){
        viewModelScope.launch {
            try {
                val response = mealdetailsRepo.getmealdetails(mealid)
                if (response.isSuccessful&&response.body()!=null){
                    _getmeailsdetails.value=response.body()!!.meals[0]
                }

            }
            catch (e:Throwable){
                Log.d("test",e.message.toString())
            }



        }
    }

    fun upsert(meal: Meal){
        viewModelScope.launch {
            mealdetailsRepo.insert(meal)
        }

    }
    fun delete(meal: Meal){
        viewModelScope.launch {
            mealdetailsRepo.delete(meal)
        }
    }
//    val saveddata=
   fun showalldatasaved():LiveData<List<Meal>>{
        return mealdetailsRepo.showalldata
    }


}