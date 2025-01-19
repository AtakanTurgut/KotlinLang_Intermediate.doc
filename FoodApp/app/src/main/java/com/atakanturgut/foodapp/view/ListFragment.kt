package com.atakanturgut.foodapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.atakanturgut.foodapp.adapter.FoodAdapter
import com.atakanturgut.foodapp.databinding.FragmentListBinding
import com.atakanturgut.foodapp.model.Food
import com.atakanturgut.foodapp.roomdb.FoodDAO
import com.atakanturgut.foodapp.roomdb.FoodDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListFragment : Fragment() {
    // View Binding
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    // Database
    private lateinit var db : FoodDatabase
    private lateinit var foodDao : FoodDAO

    // Threading - RxJava
    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(requireContext(), FoodDatabase::class.java, "Tarifler").build()
        foodDao = db.foodDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener { yeniEkle(it) }

        binding.recyclerViewFood.layoutManager = LinearLayoutManager(requireContext())
        verileriAl()
    }

    private fun verileriAl() {
        mDisposable.add(
            foodDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(tarifler: List<Food>) {
        val adapter = FoodAdapter(tarifler)
        binding.recyclerViewFood.adapter = adapter

        tarifler.forEach {
            println(it.isim)
            println(it.malzeme)
        }
    }

    fun yeniEkle(view: View){
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(bilgi = "yeni", id = -1)

        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        // Threading - RxJava
        mDisposable.clear()
    }
}