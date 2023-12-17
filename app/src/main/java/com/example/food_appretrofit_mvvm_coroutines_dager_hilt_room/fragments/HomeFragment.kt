package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters.categoryAdapter
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM.HomeViewModel
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FragmentHomeBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters.overadapter
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.R
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Ui.meals_details
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapt: overadapter
    lateinit var categoryadapter: categoryAdapter
   private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        homeViewModel.getrandommeal()
        getrandomemealbyobserver()

        homeViewModel.getovermeals()
        getovermealsobserver()
        setrecyclerview()

        homeViewModel.getcategorymeals()
        getcategorymealsbyobserver()
        setrecyclerviewofCategories()

        getdataofoveradapter()

        getdataofcategoryadapter()
        return binding.root
    }

    private fun getdataofcategoryadapter() {
        categoryadapter.onItemClickListener={
            data->
            val fragment=CategoriesFragment()
            val bundle=Bundle()
            bundle.putString("catename",data.strCategory)
            fragment.arguments=bundle
            findNavController().navigate(R.id.action_homeFragment_to_categoriesFragment,bundle)

        }
    }

    private fun getdataofoveradapter() {
        adapt.onitemclicklistner ={
            data->
            val intent=Intent(requireContext(),meals_details::class.java)
            intent.putExtra("mealid",data.idMeal)
            intent.putExtra("mealthumb",data.strMealThumb)
            intent.putExtra("title",data.strMeal)
            startActivity(intent)

        }

    }

    private fun getcategorymealsbyobserver() {
        homeViewModel.getcategorylist.observe(viewLifecycleOwner){
            date->
            categoryadapter.differ.submitList(date)
        }
    }

    private fun setrecyclerviewofCategories() {
        categoryadapter= categoryAdapter()
        binding.homeRvCategories.apply {
            layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter=categoryadapter
        }
    }

    private fun getovermealsobserver() {
        homeViewModel.getovermeals.observe(viewLifecycleOwner){
            data->
            adapt.differ.submitList(data)
        }
    }

    private fun setrecyclerview() {
        adapt= overadapter()
        binding.homeRvItems.apply {
            layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter=adapt
        }
    }


    private fun getrandomemealbyobserver() {
        homeViewModel.getrandomeal.observe(viewLifecycleOwner){
            date->
            if (date!=null){
                Glide.with(binding.root).load(date.strMealThumb).into(binding.homeImgMyRandomimg)

                try {
                    binding.homeImgMyRandomimg.setOnClickListener {
                        val intent=Intent(context,meals_details::class.java)
                        intent.putExtra("mealid",date.idMeal)
                        intent.putExtra("mealthumb",date.strMealThumb)
                        intent.putExtra("title",date.strMeal)
//                        intent.putExtra("area",date.strArea)
//                        intent.putExtra("category",date.strCategory)
//                        intent.putExtra("details",date.strInstructions)

                        startActivity(intent)

                    }

                }catch (e:Throwable){
                    Log.d("error",e.message.toString())
                }


            }
        }
    }


}