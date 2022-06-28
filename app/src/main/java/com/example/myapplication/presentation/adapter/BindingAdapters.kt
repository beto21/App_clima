package com.example.myapplication.presentation.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R

@BindingAdapter("imageUrl")
fun setImageGlide(container: AppCompatImageView, url: String?) {
    Glide.with(container.context)
        .load(url ?: R.drawable.not_found_image)
        .placeholder(R.drawable.image_loader)
        .thumbnail(0.05f)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .error(R.drawable.not_found_image)
        .into(container)


}