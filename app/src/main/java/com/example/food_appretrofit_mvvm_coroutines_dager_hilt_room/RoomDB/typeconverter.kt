package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.RoomDB

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
@TypeConverters
class typeconverter {

    @TypeConverter
    fun fromanytostring(an:Any?):String{
        if (an==null) {
            return ""
        }
       return an as String
//        return Gson().toJson(an)
    }

    @TypeConverter
    fun fromStringtoAny(an:String?):Any{
//        return Gson().fromJson(an,Any::class.java)
        if (an==null) {
            return ""
        }
     return an
    }


}