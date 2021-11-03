package com.example.imageapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.data.model.Image
import com.example.imageapp.R
import com.example.imageapp.databinding.ItemImageBinding
import com.example.imageapp.extension.loadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class ImageAdapter @Inject constructor(
) : RecyclerView.Adapter<ImageAdapter.DataViewHolder>() {

    private var urls: ArrayList<String> = ArrayList()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url: String) {
            itemView.imageIV.loadImage(url)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image, parent,
                false
            )
        )

    override fun getItemCount(): Int = urls.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(urls[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, urls[position], Toast.LENGTH_SHORT).show()
        }
    }

    fun addData(imageUrls: List<String>) {
        urls.apply {
            clear()
            addAll(imageUrls)
        }
    }

}
