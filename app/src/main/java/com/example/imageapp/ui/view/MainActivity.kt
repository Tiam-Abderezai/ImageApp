package com.example.imageapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        setContentView(R.layout.activity_main)

        setupUI()
        setupAPICall()
    }

    private fun setupUI() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImageAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupAPICall() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel.fetchImages().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { usersData -> renderList(usersData) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(images: List<Image>) {
        adapter.apply {
            addData(images)
            notifyDataSetChanged()
        }
    }
}