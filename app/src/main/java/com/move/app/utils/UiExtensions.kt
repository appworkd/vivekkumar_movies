package com.move.app.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.move.app.R

/**
 * This will handle acknowledgement messages
 */
fun View.showSnackBar(
    msg: String = "",
    length: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(
        this,
        msg,
        length
    ).show()
}

/**
 * Method for showing images
 */
fun Context.setUpImage(
    url: String,
    view: ImageView,
) {
    Glide
        .with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_image_24)
        .into(view)
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}