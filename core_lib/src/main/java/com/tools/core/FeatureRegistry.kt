package com.tools.core

interface FeatureRegistry {
    val staticFeatureList: List<StaticFeature>
    fun register(feature: StaticFeature?)
}