package com.tools.money;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tools.core.CoreLib;
import com.tools.core.Feature;
import com.tools.jackofall.MainFragment;
import com.tools.money_exchange_rate.R;

public class MoneyExchangeFeature implements Feature {

    Context context;

    public MoneyExchangeFeature(Context context) {
        this.context = context;
    }


    @Override
    public View getFeatureEntryPoint(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.entry_point_view, parent, false);
        view.setOnClickListener(v -> CoreLib.getInstance().getCommunicationLib().replaceFragment(ExchangeRateFragment.newInstance(),true));
        return view;
    }


}
