package com.tools.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.appbar.AppBarLayout

@BindingAdapter("roundedImageUrl")
fun loadImageRounded(view: ImageView, url: String?) {
    if (url == null) return
    else Glide.with(view.context)
            .load(url)
            .transform(CenterCrop(), RoundedCorners(10))
            .into(view)

}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url == null) return
    else Glide.with(view.context)
            .load(url)
            .transform(CenterCrop())
            .into(view)

}

@BindingAdapter("link")
fun loadLink(view: WebView, url: String?) {
    if (url == null) return
    else view.loadUrl(url)

}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

class CollapsibleToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }
}

