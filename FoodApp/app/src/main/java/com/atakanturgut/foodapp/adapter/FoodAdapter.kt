package com.atakanturgut.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.atakanturgut.foodapp.databinding.RecyclerRowBinding
import com.atakanturgut.foodapp.model.Food
import com.atakanturgut.foodapp.view.ListFragmentDirections

class FoodAdapter(val foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {
    class FoodHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
        return FoodHolder(recyclerRowBinding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = foodList[position].isim

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(bilgi = "eski", id = foodList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}