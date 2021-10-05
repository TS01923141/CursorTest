package com.example.cursortest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val imageList: MutableList<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private lateinit var context : Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView_image : ImageView = itemView.findViewById(R.id.imageView_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        Glide.with(context)
            .load(imageList[position])
            .into(holder.imageView_image)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}