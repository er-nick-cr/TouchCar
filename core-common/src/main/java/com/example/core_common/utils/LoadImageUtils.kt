package com.example.core_common.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.core_common.GlideApp
import com.bumptech.glide.request.target.Target

fun Context.loadImage(imageUrl: String, onSuccess: (Bitmap) -> Unit, onFailure: (GlideException?) -> Unit) {
    GlideApp.with(this)
        .asBitmap()
        .load(imageUrl)
        .listener(object : RequestListener<Bitmap> {
            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                if (resource != null) {
                    onSuccess.invoke(resource)
                }
                return true
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                onFailure.invoke(e)
                return false
            }
        }).submit()
}