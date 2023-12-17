package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.CategoryDesignBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Category

class categoryAdapter():RecyclerView.Adapter<categoryAdapter.viewholder>() {
    lateinit var onItemClickListener:((Category) -> Unit)
    private val diffUtil=object :DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory==newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem==newItem
        }

    }

    val differ=AsyncListDiffer(this,diffUtil)

    public inner class viewholder(val binding:CategoryDesignBinding):ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(CategoryDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       val arr=differ.currentList[position]
        Glide.with(holder.itemView).load(arr.strCategoryThumb).into(holder.binding.categorydesignImgCategoryimg)
        holder.binding.categorydesignTvCategoryname.text=arr.strCategory
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(arr)
        }
    }
}