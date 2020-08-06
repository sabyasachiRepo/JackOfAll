package com.tools.money

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tools.jackofall.JackOfAllApplication
import com.tools.money.injection.DaggerMoneyExchangeComponent
import com.tools.money_exchange_rate.R
import javax.inject.Inject

class ExchangeRateFragment : Fragment(), OnItemSelectedListener, View.OnClickListener {
    private lateinit var mViewModel: ExchangeRateViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var spFromCurrency: Spinner
    private lateinit var spToCurrency: Spinner
    private lateinit var btConvert: Button
    private lateinit var etAmount: EditText
    private lateinit var fromCurrency: String
    private lateinit var toCurrency: String
    private lateinit var tvConvertRate: TextView
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = (activity?.applicationContext as JackOfAllApplication).appComponent
        DaggerMoneyExchangeComponent.factory().create(appComponent).inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ExchangeRateViewModel::class.java)
        currencies
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.exchange_rate_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        spFromCurrency = view.findViewById(R.id.spinner_from_currency)
        spToCurrency = view.findViewById(R.id.spinner_to_currency)
        btConvert = view.findViewById(R.id.bt_convert)
        etAmount = view.findViewById(R.id.et_amount)
        tvConvertRate = view.findViewById(R.id.tv_convert_rate)
        spFromCurrency.onItemSelectedListener = this
        spToCurrency.onItemSelectedListener = this
        btConvert.setOnClickListener(this)
    }

    private val currencies: Unit
        private get() {
            mViewModel.getCurrencies().observe(this, Observer { currencies: List<String> ->
                val currencyAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, currencies)
                currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spFromCurrency.adapter = currencyAdapter
                spToCurrency.adapter = currencyAdapter
                val historyFromCurrency = currencyAdapter.getPosition(mViewModel.fromCurrency)
                val historyToCurrency = currencyAdapter.getPosition(mViewModel.toCurrency)
                spFromCurrency.setSelection(historyFromCurrency)
                spToCurrency.setSelection(historyToCurrency)

            })
        }


    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (parent.id == R.id.spinner_from_currency) {
            fromCurrency = parent.getItemAtPosition(position) as String
        } else {
            toCurrency = parent.getItemAtPosition(position) as String
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onClick(v: View) {
        mViewModel.fromCurrency = fromCurrency
        mViewModel.toCurrency = toCurrency
        mViewModel.getConvertRate(fromCurrency, toCurrency, etAmount.text.toString()).observe(this, Observer { result: String? -> tvConvertRate.text = result })
    }

    companion object {
        @JvmStatic
        fun newInstance(): ExchangeRateFragment {
            return ExchangeRateFragment()
        }
    }
}