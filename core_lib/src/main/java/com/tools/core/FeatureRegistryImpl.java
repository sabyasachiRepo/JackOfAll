package com.tools.core;

import java.util.ArrayList;
import java.util.List;

public class FeatureRegistryImpl implements FeatureRegistry {

    private static FeatureRegistryImpl featureRegistry;

    private List<StaticFeature> featureList = new ArrayList<>();

    private FeatureRegistryImpl() {
    }

    public static FeatureRegistry getInstance() {
        if (featureRegistry == null) {
            synchronized (FeatureRegistryImpl.class) {
                if (featureRegistry == null) {
                    featureRegistry = new FeatureRegistryImpl();
                }
            }
        }

        return featureRegistry;
    }

    @Override
    public List<StaticFeature> getStaticFeatureList() {
        return featureList;
    }

    @Override
    public void register(StaticFeature feature) {
        featureList.add(feature);
    }
}
