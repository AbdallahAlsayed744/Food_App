package com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.Adapters.favorits_adapter
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.MVVM.mealdetails_viewmodel
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.R
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.databinding.FragmentFavoritsBinding
import com.example.food_appretrofit_mvvm_coroutines_dager_hilt_room.pojo.Meal
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritsFragment : Fragment() {
    lateinit var binding: FragmentFavoritsBinding
    lateinit var adapte:favorits_adapter
    val model:mealdetails_viewmodel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFavoritsBinding.inflate(inflater,container,false)
        getallsavedmeals()
        preparerecyclerview()
        val itemTouchHelper=object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            )=true


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val meal: Meal
                model.delete(adapte.differ.currentList[position])
                Snackbar.make(requireView(),"deleted this", Snackbar.LENGTH_LONG).setAction(
                    "Undo",View.OnClickListener {
                        model.upsert(adapte.differ.currentList[position])
                    }
                ).show()
            }


        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.RvFavorits)


        return binding.root
    }

    private fun preparerecyclerview() {
        adapte= favorits_adapter()
        binding.RvFavorits.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=adapte
        }
    }

    private fun getallsavedmeals() {
        model.showalldatasaved().observe(viewLifecycleOwner){
            data->
            adapte.differ.submitList(data)
            binding.numberOfFavorits.text=data.size.toString()


        }
    }


}