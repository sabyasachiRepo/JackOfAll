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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tools.core.BaseFragment
import com.tools.core.network.Status
import com.tools.jackofall.JackOfAllApplication
import com.tools.money.injection.DaggerMoneyExchangeComponent
import com.tools.money_exchange_rate.R
import com.tools.money_exchange_rate.databinding.ExchangeRateFragmentBinding
import javax.inject.Inject

class ExchangeRateFragment : BaseFragment<ExchangeRateViewModel>(), OnItemSelectedListener {
    override lateinit var viewModel: ExchangeRateViewModel

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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExchangeRateViewModel::class.java)
        binding.viewModel = viewModel
        getCurrencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ExchangeRateFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }
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
        viewModel.getCurrencies().observe(viewLifecycleOwner, Observer {

            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val currencyAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, resource.data?.currencies?.keys?.toList()
                                ?: emptyList())
                        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spFromCurrency.adapter = currencyAdapter
                        spToCurrency.adapter = currencyAdapter
                        val historyFromCurrency = currencyAdapter.getPosition(viewModel.fromCurrency)
                        val historyToCurrency = currencyAdapter.getPosition(viewModel.toCurrency)
                        spFromCurrency.setSelection(historyFromCurrency)
                        spToCurrency.setSelection(historyToCurrency)
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })

    }


    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (parent.id == R.id.spinner_from_currency) {
            viewModel.fromCurrency = parent.getItemAtPosition(position) as String
        } else {
            viewModel.toCurrency = parent.getItemAtPosition(position) as String
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