package com.tools.core.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url == null) return
    else Glide.with(view.context)
            .load(url)
            .transform(CenterCrop(), RoundedCorners(10))
            .into(view)

}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}