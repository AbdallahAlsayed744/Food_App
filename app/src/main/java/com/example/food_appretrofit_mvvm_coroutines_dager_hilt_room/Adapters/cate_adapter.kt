package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FavoriteCustomDesignBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.over

class cate_adapter(): RecyclerView.Adapter<cate_adapter.vh>() {
    private val diffUtil=object : DiffUtil.ItemCallback<over>(){
        override fun areItemsTheSame(oldItem: over, newItem: over): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: over, newItem: over): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,diffUtil)

    inner class vh(val binding: FavoriteCustomDesignBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(FavoriteCustomDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
       val ar=differ.currentList[position]
        Glide.with(holder.itemView).load(ar.strMealThumb).into(holder.binding.favotiteText)
        holder.binding.mealName.text=ar.strMeal
    }

}
