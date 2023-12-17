package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FavoriteCustomDesignBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal

class favorits_adapter():RecyclerView.Adapter<favorits_adapter.vh>() {
    private val diffUtil=object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffUtil)

    inner class vh(val binding:FavoriteCustomDesignBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(FavoriteCustomDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        val arr=differ.currentList[position]
        Glide.with(holder.itemView).load(arr.strMealThumb).into(holder.binding.favotiteText)
        holder.binding.mealName.text=arr.strMeal
    }
}