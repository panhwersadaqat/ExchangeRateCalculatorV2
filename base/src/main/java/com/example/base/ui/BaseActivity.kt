package com.example.base.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.base.R
import com.example.base.broadcast_receivers.InternetStateReceiver
import com.example.base.events.BaseDataEvents
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

    var messageDialog : AlertDialog? = null

//    @Inject
//    lateinit var bindingComponentImpl: DataBindingComponentImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*try {
            super.onCreate(savedInstanceState)
        }catch (e:Exception) {
            changeConf()
        }*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = DataBindingUtil.setContentView(this, layoutID())
        initializeComponents()
        setObservers()
        setUpListeners()

    }

/*    fun changeConf() {
        when (this.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                openActivity(
                    AuthenticationActivity::class.java,
                    false
                )
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                showToast("redirect app")
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                showToast("redirect app")
            }
        }
    }*/

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
        viewModel.obDataEvent.observe(this, Observer {
            var event = it.getEventIfNotHandled()
            if (event != null)
                when (event) {
                    is BaseDataEvents.Exception -> showException(event.message)
                    is BaseDataEvents.Error -> {
                        if(viewModel.isNotify) {
                            showError(event.message)
                        }
                    }
                    is BaseDataEvents.CustomError -> {
                        showFailureToast(event.message)
                    }
                    is BaseDataEvents.Toast -> showToast(event.message)
                    is BaseDataEvents.Toast -> showSuccessToast(event.message)
                    BaseDataEvents.ForceLogout -> forceLogout()
                    is BaseDataEvents.ShowSuccessDialog-> {showMessageDialog()}
                    else -> {}
                }
        })

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

    protected open fun showException(message: String) {
        showToast(message, R.drawable.error_icon)
    }

    protected open fun showError(message: String) {
        showToast(message,R.drawable.error_icon)
    }
    protected open fun showSuccessToast(message: String) {
        showToast(message,R.drawable.success_icon)
    }
    protected fun showFailureToast(message: String) {
        showToast(message, R.drawable.error_icon)
    }



    fun showMessageDialog(){
        messageDialog?.show()
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