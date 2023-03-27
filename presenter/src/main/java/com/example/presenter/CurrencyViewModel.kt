package com.example.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.events.BaseEvent
import com.example.base.viewmodels.BaseViewModel
import com.model.request.CurrencyRequest
import com.model.response.CurrencyResponse
import com.usecase.CurrencyUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val authCodeUC: CurrencyUC,
) : BaseViewModel() {

    private val _events  = MutableLiveData<BaseEvent<CurrencyEvents>>()
    val events : LiveData<BaseEvent<CurrencyEvents>> = _events

    val convertedRate = MutableLiveData<Double>()
    var request = CurrencyRequest()
    fun getCurrencyList() {
        showLoader()
        viewModelScope.launch {
            authCodeUC(request).catch { exception ->
                handleExceptions(exception)
                onGetCurrencyFailed(exception.message.toString())
            }.collect {
                onGetCurrencySuccess(it)
            }
        }
    }

    fun onGetCurrencySuccess(response :CurrencyResponse?){
        _events.value = BaseEvent(CurrencyEvents.GetCurrencyList(response))
    }

    fun onGetCurrencyFailed(msg: String){
        _events.value = BaseEvent(CurrencyEvents.OnFailed(msg))
    }

}