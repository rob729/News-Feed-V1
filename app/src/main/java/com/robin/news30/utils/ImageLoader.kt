package com.robin.news30.utils

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.robin.news30.R
import com.techyourchance.dagger2course.common.dependnecyinjection.activity.ActivityScope
import javax.inject.Inject

@ActivityScope
class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {

    private val requestOptions = RequestOptions
        .placeholderOf(R.drawable.ic_loading)
        .error(R.drawable.ic_error)
        .centerCrop()

    fun loadImage(target: ImageView, imageUrl: String) {
        Glide.with(activity).load(imageUrl).apply(requestOptions).into(target)
    }
}