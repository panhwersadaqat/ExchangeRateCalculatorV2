package com.example.base.extensions

import android.view.View
import com.example.base.util.SafeClickListener

fun View.visible()
{
    this.visibility = View.VISIBLE
}

fun View.gone()
{
    this.visibility = View.GONE
}

fun View.invisible()
{
    this.visibility = View.INVISIBLE
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}