package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.R
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottom_nav=binding.bottonNav
        val navcontroller=Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(bottom_nav,navcontroller)


    }
}