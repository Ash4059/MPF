package com.example.MPF.Ingresess;

import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.Ingress;

import java.util.Objects;

public class KafkaIngress extends Ingress {

    private static KafkaIngress instance;

    public KafkaIngress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public void initialize(Config config) {
        initialized = true;
        this.config = config;
    }

    @Override
    public void close() {
        initialized = false;
        instance = null;
    }

    public static KafkaIngress getInstance(){
        return instance;
    }

    public static boolean isInitialized(){
        return Objects.nonNull(instance) && instance.initialized;
    }
}
