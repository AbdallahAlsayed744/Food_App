package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM.mealdetails_viewmodel
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.R
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.ActivityMealsDetailsBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.CategoryDesignBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class meals_details : AppCompatActivity() {
    val model:mealdetails_viewmodel by viewModels()
    lateinit var mealid:String
    lateinit var youtube:String
    lateinit var binding: ActivityMealsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMealsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getdetalisfromhomefragment()
        model.getmeailsdetails(mealid)
        getmeailsdetailsbyobserver()

        binding.floatingbutton.setOnClickListener {
            saveddate?.let {
                model.upsert(it)
                Toast.makeText(this, "Saved in favorits", Toast.LENGTH_LONG).show()
            }
        }


    }
    var saveddate:Meal?=null
    private fun getmeailsdetailsbyobserver() {
        model.getmeailsdetails.observe(this){
            data->
            saveddate=data
            binding.tvArea.text=data.strArea
            binding.tvCategory.text=data.strCategory
            binding.tvDetails.text=data.strInstructions
            youtube= data.strYoutube.toString()
            binding.video.setOnClickListener {
                val intent=Intent(Intent.ACTION_VIEW, Uri.parse(youtube))
                startActivity(intent)
            }

        }
    }

    private fun getdetalisfromhomefragment() {
        val img_collapse=intent.getStringExtra("mealthumb").toString()
        val title=intent.getStringExtra("title")
        mealid=intent.getStringExtra("mealid").toString()
//        val area=intent.getStringExtra("area")
//        val category=intent.getStringExtra("category")
//        val details=intent.getStringExtra("details")
//

        Glide.with(binding.root).load(img_collapse).into(binding.collabseImg)
//        binding.tvDetails.text=details
        binding.collapsce.title=title
//        binding.tvCategory.text=category
//        binding.tvArea.text=area
    }
}