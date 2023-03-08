package com.example.presenter


import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.base.ui.BaseActivity
import com.example.presenter.databinding.ActivityCurrencyBinding
import com.google.android.material.snackbar.Snackbar
import com.model.response.Rates
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CurrencyActivity : BaseActivity(){

    private val viewModel : CurrencyViewModel by viewModels()

    //Selected country string, default is Afghanistan, since its the first country listed in the spinner
    private var selectedItem1: String? = "AFN"
    private var selectedItem2: String? = "AFN"
    override fun initializeComponents() {
        initSpinner()
    }

    override fun setObservers() {
        val _viewBinding = binding as ActivityCurrencyBinding
        observeDataEvents(viewModel)
        viewModel.events.observe(this) {
            val event = it.getEventIfNotHandled()
            when(event) {
                is CurrencyEvents.GetCurrencyList -> {
                        val map: Map<String, Rates>

                        map = event.response?.rates!!

                        map.keys.forEach {

                            val rateForAmount = map[it]?.rate_for_amount

                            viewModel.convertedRate.value = rateForAmount

                            //format the result obtained e.g 1000 = 1,000
                            val formattedString = String.format("%,.2f", viewModel.convertedRate.value)

                            //set the value in the second edit text field
                            _viewBinding.etSecondCurrency.setText(formattedString)

                        }

                        //stop progress bar
                        _viewBinding.prgLoading.visibility = View.GONE
                        //show button
                        _viewBinding.btnConvert.visibility = View.VISIBLE
                    }

                is CurrencyEvents.OnFailed -> {
                    showToast(event.msg)
                    _viewBinding.prgLoading.visibility = View.GONE
                    //show button
                    _viewBinding.btnConvert.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

    override fun layoutID() = R.layout.activity_currency

    override fun forceLogout() {

    }

    override fun setUpListeners() {
        val _viewbinding = binding as ActivityCurrencyBinding
        //Convert button clicked - check for empty string and internet then do the conersion
        _viewbinding.btnConvert.setOnClickListener {

            //check if the input is empty
            val numberToConvert = _viewbinding.etFirstCurrency.text.toString()

            if(numberToConvert.isEmpty() || numberToConvert == "0"){
                Snackbar.make(_viewbinding.mainLayout,"Input a value in the first text field, result will be shown in the second text field", Snackbar.LENGTH_LONG)
                    //.withColor(ContextCompat.getColor(this, R.color.dark_red))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .show()
            }

            //check if internet is available
            else if (!Utility.isNetworkAvailable(this)){
                Snackbar.make(_viewbinding.mainLayout,"You are not connected to the internet", Snackbar.LENGTH_LONG)
                    //.withColor(ContextCompat.getColor(this, R.color.dark_red))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .show()
            }

            //carry on and convert the value
            else{
                doConversion()
            }
        }
    }

    /**
     * This method does everything required for handling spinner (Dropdown list) - showing list of countries, handling click events on items selected.*
     */

    private fun initSpinner(){
        val _viewBinding = binding as ActivityCurrencyBinding
        //get first spinner country reference in view
        val spinner1 = _viewBinding.spnFirstCountry

        //set items in the spinner i.e a list of all countries
        spinner1.setItems( getAllCountries() )

        //hide key board when spinner shows (For some weird reasons, this isn't so effective as I am using a custom Material Spinner)
        spinner1.setOnClickListener {
            Utility.hideKeyboard(this)
        }

        //Handle selected item, by getting the item and storing the value in a  variable - selectedItem1
        spinner1.setOnItemSelectedListener { view, position, id, item ->
            //Set the currency code for each country as hint
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            selectedItem1 = currencySymbol
            _viewBinding.txtFirstCurrencyName.setText(selectedItem1)
        }


        //get second spinner country reference in view
        val spinner2 = _viewBinding.spnSecondCountry

        //hide key board when spinner shows
        spinner1.setOnClickListener {
            Utility.hideKeyboard(this)
        }

        //set items on second spinner i.e - a list of all countries
        spinner2.setItems( getAllCountries() )


        //Handle selected item, by getting the item and storing the value in a  variable - selectedItem2,
        spinner2.setOnItemSelectedListener { view, position, id, item ->
            //Set the currency code for each country as hint
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            selectedItem2 = currencySymbol
            _viewBinding.txtSecondCurrencyName.setText(selectedItem2)
        }

    }


    /**
     * A method for getting a country's currency symbol from the country's code
     * e.g USA - USD
     */

    private fun getSymbol(countryCode: String?): String? {
        val availableLocales = Locale.getAvailableLocales()
        for (i in availableLocales.indices) {
            if (availableLocales[i].country == countryCode
            ) return Currency.getInstance(availableLocales[i]).currencyCode
        }
        return ""
    }

    /**
     * A method for getting a country's code from the country name
     * e.g Nigeria - NG
     */

    private fun getCountryCode(countryName: String) = Locale.getISOCountries().find { Locale("", it).displayCountry == countryName }


    /**
     * A method for getting all countries in the world - about 256 or so
     */

    private fun getAllCountries(): ArrayList<String> {

        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        countries.sort()

        return countries
    }

    /**
     * A method that does the conversion by communicating with the API - fixer.io based on the data inputed
     * Uses viewModel and flows
     */

    private fun doConversion(){
        val _viewBinding = binding as ActivityCurrencyBinding
        //hide keyboard
        Utility.hideKeyboard(this)

        //make progress bar visible
        _viewBinding.prgLoading.visibility = View.VISIBLE

        //make button invisible
        _viewBinding.btnConvert.visibility = View.GONE

        //Get the data inputed
        val apiKey = "c9084751116dfcb4ab1f189faa539343f8f51853"
        val from = selectedItem1.toString()
        val to = selectedItem2.toString()
        val amount = _viewBinding.etFirstCurrency.text.toString().toDouble()

        //do the conversion
        viewModel.request.api_key = apiKey
        viewModel.request.from = from
        viewModel.request.to = to
        viewModel.request.amount = amount
        viewModel.getCurrencyList()

        //observe for changes in UI
        //observeUi()

    }

    /**
     * Using coroutines flow, changes are observed and responses gotten from the API
     *
     */
}

