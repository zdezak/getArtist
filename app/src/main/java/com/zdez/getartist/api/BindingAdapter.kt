package com.zdez.getartist.api

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zdez.getartist.R

@BindingAdapter("url")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        if (imgUrl.isNullOrEmpty()) {
            imgView.setImageResource(R.drawable.empty_image)
        } else {
            val uri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(uri)
                .into(imgView)
        }

    }
}
