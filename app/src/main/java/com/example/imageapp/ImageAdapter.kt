package com.example.imageapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.databinding.ItemImageBinding
import com.example.imageapp.extension.loadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*


class ImageAdapter(private val context: Context?, private var items: List<Image>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int) = items[position]


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_image, parent, false)
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)


        return ViewHolder(binding)
    }

    class ViewHolder(binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgImage: ImageView? = null

        init {
            imgImage = binding.imageIV
        }
    }


    private var images = mutableListOf<Image>()

    init {
        images.addAll(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var images = items[position]


        holder.itemView.imageIV.loadImage(images.imageUrl)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, images.imageUrl, Toast.LENGTH_SHORT).show()
        }

    }


}
