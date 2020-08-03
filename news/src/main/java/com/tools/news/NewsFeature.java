package com.tools.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tools.core.Feature;

public class NewsFeature implements Feature {

    Context context;

    public NewsFeature(Context context) {
        this.context = context;
    }


    @Override
    public View getFeatureEntryPoint(ViewGroup parent) {
        //View view = LayoutInflater.from(context).inflate(R.layout.news_entry_point_view, parent, false);
        //view.setOnClickListener(v -> CoreLib.getInstance().getCommunicationLib().replaceFragment(NewsArticleListFragment.newInstance(1), true));
        return null;
    }


}
