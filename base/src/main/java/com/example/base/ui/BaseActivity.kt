package com.example.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.base.broadcast_receivers.InternetStateReceiver
import com.example.base.factory.ToastFactory
import com.example.base.viewmodels.BaseViewModel
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {
    val TAG: String = this.javaClass.simpleName

    var isInternetConnected = false

    @Inject
    lateinit var toastFactory: ToastFactory

    @Inject
    lateinit var internetStateReceiver: InternetStateReceiver

    lateinit var binding: ViewDataBinding


//    @Inject
//    lateinit var bindingComponentImpl: DataBindingComponentImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = DataBindingUtil.setContentView(this, layoutID())
        initializeComponents()
        setObservers()
        setUpListeners()

    }

    open fun setUpListeners() {}
    protected abstract fun initializeComponents()
    protected abstract fun setObservers()
    protected abstract fun layoutID(): Int



    /**
     *This function will be called by base activity if account manager
     * doesnt contain account, This function will call viewModel's forceLagou
     * which will clear local database
     */
    protected abstract fun forceLogout()

    open fun onInternetConnected() {
        isInternetConnected = true
    }

    open fun onInternetDisconnected() {
        isInternetConnected = false
    }

    fun observeDataEvents(viewModel: BaseViewModel) {
        viewModel.isNetworkStatusNotify.observe(this) {
            var event = it.getEventIfNotHandled()
            if (event != null) {
                viewModel.isNotify = event
            }
        }

    }


    protected fun showToast(message: String) {
        toastFactory.create(message)
    }
    protected fun showToast(message: String,id:Int) {
        toastFactory.create(message,id)
    }




    override fun onResume() {
        super.onResume()
//        adding client to get network connectivity events
        internetStateReceiver.addReciver(this)
    }

    override fun onStop() {
        super.onStop()
//        removing internet connection client
        internetStateReceiver.removeReceiver(this)

    }



}