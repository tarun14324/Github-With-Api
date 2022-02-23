package com.example.githubsearch.utilities

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun View.hideKeyboard(context: Context?) {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

//fun Context.showToast(message: String, duration: Int) {
//    Toast.makeText(this, message, duration).show()
//}


object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("app:updateVisibility")
    fun updateVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }


    @BindingAdapter("Toast")
    fun toast(view: View, message: String) {
        Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
    }


}


