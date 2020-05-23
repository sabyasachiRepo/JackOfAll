package com.tools.jackofall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tools.core.BaseActivity;
import com.tools.core.CommunicationLib;
import com.tools.core.CoreLib;

public class MainActivity extends BaseActivity implements CommunicationLib {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoreLib.getInstance().setCommunicationLib(this);
       replaceFragment(MainFragment.newInstance(),false);
    }

    @Override
    public void replaceFragment(Fragment fragment,boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, null);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
