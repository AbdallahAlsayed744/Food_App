package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.DI

import android.app.Application
import androidx.room.Room
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Api.call_api
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.RoomDB.meal_database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Retrofit_Builder {

    @Provides
    @Singleton
    fun retrobuild():call_api=
        Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(call_api::class.java)


    @Provides
    @Singleton
    fun providedbroom(application: Application):meal_database=
        Room.databaseBuilder(application,meal_database::class.java,"meals.db").build()

}