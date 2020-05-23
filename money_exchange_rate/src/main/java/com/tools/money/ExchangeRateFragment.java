package com.tools.money;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.tools.money_exchange_rate.R;

import java.util.List;


public class ExchangeRateFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ExchangeRateViewModel mViewModel;
    private Spinner spFromCurrency;
    private Spinner spToCurrency;
    private Button btConvert;
    private EditText etAmount;
    private String fromCurrency;
    private String toCurrency;

    public static ExchangeRateFragment newInstance() {
        return new ExchangeRateFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModel.class);
        getCurrencies();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exchange_rate_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        spFromCurrency = view.findViewById(R.id.spinner_from_currency);
        spToCurrency = view.findViewById(R.id.spinner_to_currency);
        btConvert = view.findViewById(R.id.convert);
        etAmount = view.findViewById(R.id.et_amount);

        spFromCurrency.setOnItemSelectedListener(this);
        spToCurrency.setOnItemSelectedListener(this);

        btConvert.setOnClickListener(this);
    }

    private void getCurrencies() {
        mViewModel.getCurrencies().observe(this, currencies -> {
            ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, currencies);
            currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spFromCurrency.setAdapter(currencyAdapter);
            spToCurrency.setAdapter(currencyAdapter);


        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_from_currency) {
            fromCurrency = (String) parent.getItemAtPosition(position);
        } else {
            toCurrency = (String) parent.getItemAtPosition(position);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        mViewModel.getConvertRate(fromCurrency, toCurrency, etAmount.getText().toString()).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

            }
        });
    }
}
