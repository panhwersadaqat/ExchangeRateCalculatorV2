package com.example.presenter


import androidx.activity.viewModels
import com.example.base.ui.BaseActivity
import com.example.presenter.databinding.ActivityCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyActivity : BaseActivity(){

    private val viewModel : CurrencyViewModel by viewModels()

    override fun initializeComponents() {
        viewModel.getCurrencyList()
    }

    override fun setObservers() {
        observeDataEvents(viewModel)
        viewModel.events.observe(this) {
            val event = it.getEventIfNotHandled()
            when(event) {
                is CurrencyEvents.GetCurrencyList -> {
                    //fillNotificationTypeList(event.response?: emptyList())
                    showToast("success")
                }
                else -> {}
            }
        }
    }

    override fun layoutID() = R.layout.activity_currency

    override fun forceLogout() {

    }

    override fun setUpListeners() {
        val view = binding as ActivityCurrencyBinding
        view.btnConvert.setOnClickListener {
            /*viewModel.convert(
                view.etFrom.text.toString(),
                view.spFromCurrency.selectedItem.toString(),
                view.spToCurrency.selectedItem.toString(),
            )*/
        }
    }
}

