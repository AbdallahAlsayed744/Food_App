package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.OverDesignBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.over

class overadapter():RecyclerView.Adapter<overadapter.viewholder>() {
    lateinit var onitemclicklistner:((over)-> Unit)
    val diff=object :DiffUtil.ItemCallback<over>(){
        override fun areItemsTheSame(oldItem: over, newItem: over): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: over, newItem: over): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diff)


     class viewholder( val binding:OverDesignBinding):ViewHolder(binding.root){

     }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(OverDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       val arr=differ.currentList[position]
        Glide.with(holder.itemView).load(arr.strMealThumb).into(holder.binding.overDesignImg)
        holder.itemView.setOnClickListener {

            onitemclicklistner.invoke(arr)
        }
    }
}