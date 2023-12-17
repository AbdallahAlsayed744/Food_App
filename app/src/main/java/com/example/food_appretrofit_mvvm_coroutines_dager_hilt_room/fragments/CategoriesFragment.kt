package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters.cate_adapter
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM.cate_viewmodel
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.R
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FragmentCateBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FragmentCategoriesBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    lateinit var binding: FragmentCategoriesBinding
    lateinit var catename:String
    lateinit var adapte: cate_adapter
    val model: cate_viewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCategoriesBinding.inflate(layoutInflater,container,false)
//        findNavController().navigate(R.id.action_categoriesFragment_to_homeFragment)

        getdatabybundle()
        model.getmealtype(catename)
        getmealstypewbyobserver()
        preaparerecyclerview()


        return binding.root
    }
    private fun preaparerecyclerview() {
        adapte= cate_adapter()
        binding.cateRv.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=adapte
        }
    }

    private fun getmealstypewbyobserver() {
        model.getmealtype.observe(viewLifecycleOwner){
                data->
            adapte.differ.submitList(data)

        }
    }

    private fun getdatabybundle() {
        val data=arguments
        if (data!=null){
            catename=data.getString("catename").toString()
        }
    }


}