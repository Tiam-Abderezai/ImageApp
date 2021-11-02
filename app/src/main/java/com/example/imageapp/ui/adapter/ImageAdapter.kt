package com.example.imageapp.ui.adapter

import android.content.Context
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
import kotlinx.android.synthetic.main.item_image.view.*
import javax.inject.Inject


//class ImageAdapter(private val context: Context?, private var items: List<Image>) :
//    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
//
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    fun getItem(position: Int) = items[position]
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent?.context)
//            .inflate(R.layout.item_image, parent, false)
//        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//
//
//        return ViewHolder(binding)
//    }
//
//    class ViewHolder(binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
//        var imgImage: ImageView? = null
//
//        init {
//            imgImage = binding.imageIV
//        }
//    }
//
//
//    private var images = mutableListOf<Image>()
//
//    init {
//        images.addAll(items)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val images = items[position]
//
//
//        holder.itemView.imageIV.loadImage(images.imageUrl)
//
//        holder.itemView.setOnClickListener {
//            Toast.makeText(context, images.imageUrl, Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//}


class ImageAdapter @Inject constructor(
) : RecyclerView.Adapter<ImageAdapter.DataViewHolder>() {

    private var images: ArrayList<Image> = ArrayList()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image) {
                    itemView.imageIV.loadImage(image.imageUrl)
////            itemView.textViewUserName.text = user.name
////            itemView.textViewUserEmail.text = user.email
////            Glide.with(itemView.imageViewAvatar.context)
////                .load(user.avatar)
////                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image, parent,
                false
            )
        )

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(images[position])

    fun addData(users: List<Image>) {
        this.images.apply {
            clear()
            addAll(users)
        }
    }

}