package com.example.imageapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageapp.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProvider
import androidx.activity.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.imageapp.R
import com.example.imageapp.Status
import com.example.imageapp.data.model.Image
import com.example.imageapp.ui.adapter.ImageAdapter

import com.example.imageapp.ui.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: ImageViewModel by viewModels()
    @Inject
    lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupAPICall()
    }

    private fun setupUI() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
    }

    private fun setupAPICall() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel.fetchImages().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.i("MainActivity", "Success: ${it.message}")
                    binding.progressBar.visibility = View.INVISIBLE
                    it.data?.let { usersData -> renderList(usersData) }
                    binding.recyclerView.visibility = View.INVISIBLE
                }
                Status.LOADING -> {
                    Log.i("MainActivity", "Loading: ${it.message}")
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    Log.d("MainActivity", "Error: ${it.message}")
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<String>) {
        adapter.apply {
            addData(users)
            notifyDataSetChanged()
        }
    }
}