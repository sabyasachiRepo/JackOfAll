package com.tools.money

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tools.jackofall.JackOfAllApplication
import com.tools.money.injection.DaggerMoneyExchangeComponent
import com.tools.money_exchange_rate.R
import com.tools.money_exchange_rate.databinding.ExchangeRateFragmentBinding
import javax.inject.Inject

class ExchangeRateFragment : Fragment(), OnItemSelectedListener {
    private lateinit var mViewModel: ExchangeRateViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var spFromCurrency: Spinner
    private lateinit var spToCurrency: Spinner
    private lateinit var binding: ExchangeRateFragmentBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = (activity?.applicationContext as JackOfAllApplication).appComponent
        DaggerMoneyExchangeComponent.factory().create(appComponent).inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ExchangeRateViewModel::class.java)
        binding.viewModel = mViewModel
        getCurrencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ExchangeRateFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        initView()
        return binding.root
    }

    private fun initView() {
        spFromCurrency = binding.spinnerFromCurrency
        spToCurrency = binding.spinnerToCurrency
        spFromCurrency.onItemSelectedListener = this
        spToCurrency.onItemSelectedListener = this
    }

    private fun getCurrencies() {
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
            mViewModel.fromCurrency = parent.getItemAtPosition(position) as String
        } else {
            mViewModel.toCurrency = parent.getItemAtPosition(position) as String
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}


    companion object {
        @JvmStatic
        fun newInstance(): ExchangeRateFragment {
            return ExchangeRateFragment()
        }
    }
}