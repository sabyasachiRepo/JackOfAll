package com.tools.core;

import com.tools.core.Feature;

import java.util.List;

public interface FeatureRegistry {
    List<Feature> getFeatureList();
    void register(Feature feature);
}
