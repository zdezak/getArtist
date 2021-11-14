package com.zdez.getartist.API

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val uri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(uri)
            .into(imgView)
    }
}
