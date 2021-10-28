package com.example.imageapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ImageAdapter(private val context: Context?, private var items: List<Image>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int) = items[position]


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_image, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgImage: ImageView? = null

        init {
            this.imgImage = view?.findViewById(R.id.imageIV)
        }
    }


    private var images = mutableListOf<Image>()

    init {
        images.addAll(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var images = items[position]

        Picasso.get().load(images.imageUrl).into(holder?.imgImage)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, images.imageUrl, Toast.LENGTH_SHORT).show()
        }

    }


}