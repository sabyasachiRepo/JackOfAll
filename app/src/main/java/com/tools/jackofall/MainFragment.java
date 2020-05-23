package com.tools.jackofall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.tools.core.Feature;
import com.tools.core.FeatureRegistryImpl;


public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        addFeatures(view);
        return view;
    }

    private void addFeatures(View view) {
        if(view instanceof LinearLayout){
            LinearLayout linearLayout = (LinearLayout) view;
            for (Feature feature : FeatureRegistryImpl.getInstance().getFeatureList()) {
                linearLayout.addView(feature.getFeatureEntryPoint(linearLayout));
            }
        }
    }
}
