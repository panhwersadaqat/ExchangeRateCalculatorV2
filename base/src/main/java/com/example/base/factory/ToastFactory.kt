package com.example.base.factory

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.base.R
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ToastFactory @Inject constructor(@ApplicationContext private var context: Context) {

    private lateinit var toast: Toast
    private lateinit var view: View

    fun create(message: String,id:Int? = 0) {

        if (::toast.isInitialized && toast.view?.isShown!!) toast.cancel()

        toast = Toast(context)

        if (!::view.isInitialized)
            view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null)

        toast.view = view


        toast.duration = Toast.LENGTH_SHORT
        toast.view?.findViewById<TextView>(R.id.message)?.setText(message)
        if(id != null && id != 0) {
            toast.view?.findViewById<ImageView>(R.id.icon)?.visibility = View.VISIBLE
            toast.view?.findViewById<ImageView>(R.id.icon)?.setImageResource(id!!)
        }
        else {
            toast.view?.findViewById<ImageView>(R.id.icon)?.visibility = View.GONE
        }
        //if(ontop) {
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 200)
        //}
        toast.show()
    }
    fun create(message: String,id:Int? = 0,length: Int? = 0) {

        if (::toast.isInitialized && toast.view?.isShown!!) toast.cancel()

        toast = Toast(context)

        if (!::view.isInitialized)
            view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null)

        toast.view = view
        var toastId: Int = Toast.LENGTH_SHORT
        if(length == 1) {
            toastId = Toast.LENGTH_LONG
        }
        toast.duration = toastId
        toast.view?.findViewById<TextView>(R.id.message)?.setText(message)
        if(id != null && id != 0) {
            toast.view?.findViewById<ImageView>(R.id.icon)?.visibility = View.VISIBLE
            toast.view?.findViewById<ImageView>(R.id.icon)?.setImageResource(id!!)
        }
        else {
            toast.view?.findViewById<ImageView>(R.id.icon)?.visibility = View.GONE
        }
        //if(ontop) {
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 200)
        //}
        toast.show()
    }

}