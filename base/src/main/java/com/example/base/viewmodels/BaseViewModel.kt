package com.example.base.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.events.BaseDataEvents
import com.example.base.events.BaseEvent
import com.domain.exceptions.*
import java.io.EOFException

open class BaseViewModel : ViewModel() {
    val TAG = this::class.java.name
    protected val _dataEvent: MutableLiveData<BaseEvent<BaseDataEvents>> = MutableLiveData()
    val obDataEvent: LiveData<BaseEvent<BaseDataEvents>> = _dataEvent

    protected val _isNetworkStatusNotify: MutableLiveData<BaseEvent<Boolean>> = MutableLiveData()
    val isNetworkStatusNotify: LiveData<BaseEvent<Boolean>> = _isNetworkStatusNotify
    var isNotify = true
    suspend fun handleExceptions(exception: Throwable): HashMap<String, String> {
        val errors: HashMap<String, String> = HashMap()
        when (exception) {
            is ConnectivityException, is _404Exception, is ServerException ->{
                showError(exception.message ?: "")
                errors.put("error", exception.message ?: "")
            }
            is _401Exception -> {
                showError(exception.message ?: "")
                errors.put("error", exception.message ?: "")
                forceLogout()
            }

            is UnProcessableEntityException -> {
                errors.putAll(exception.errorMap)
                exception.errorMap.forEach {
                    showError(it.value)
                }
            }
            is EOFException -> {
                errors.put("error", exception.message ?: "")
            }
            else->{
                showError(exception.message ?: "")
                errors.put("error", exception.message ?: "")
            }
        }
        return errors
    }

    protected fun showError(message: String) {
        if(message.contains("Unauthenticated") || message.contains("Unauthorized")){
            forceLogout()
        }
        else
            _dataEvent.value = (BaseEvent(BaseDataEvents.Error(message)))
    }

    protected fun showException(exception: Exception) {

    }

    protected fun showLoader() {
        _dataEvent.value = BaseEvent(BaseDataEvents.ShowLoader)
    }

    protected fun hideLoader() {
        _dataEvent.value = BaseEvent(BaseDataEvents.HideLoader)
    }

    protected fun showToast(message: String) {
        _dataEvent.value = BaseEvent(BaseDataEvents.Toast(message))
    }
    protected fun showCustomErrorToast(message: String) {
        _dataEvent.value = BaseEvent(BaseDataEvents.CustomError(message))
    }
    protected fun showToastSuccess(message: String) {
        _dataEvent.value = BaseEvent(BaseDataEvents.ToastSuccess(message))
    }
    private fun logout() {
        _dataEvent.value = BaseEvent(BaseDataEvents.ForceLogout)
    }

    protected fun showSuccessDialog(message: String,messageDetail:String,buttonText:String = "",actionListenr : ()->Unit) {
        _dataEvent.value = BaseEvent(BaseDataEvents.ShowSuccessDialog(message,messageDetail,buttonText,actionListenr))
    }

    //    this method is dummy just to get forcelogout in baseViewModel,
//    actual method exist in BaseActivityViewModel which will be called by Base Activity whenever
//    logout is requried for example unauthenticated error
    open fun forceLogout() {
        _dataEvent.value = BaseEvent(BaseDataEvents.ForceLogout)
    }
}