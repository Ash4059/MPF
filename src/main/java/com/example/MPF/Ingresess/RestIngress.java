package com.example.MPF.Ingresess;

import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.Ingress;

import java.util.Objects;

public class RestIngress extends Ingress {

    private static RestIngress instance;

    public RestIngress(Config config){
        this.initialize(config);
        instance = this;
    }

    public static RestIngress getInstance(){
        return instance;
    }

    @Override
    public void initialize(Config config) {
        this.initialized = true;
        this.config = config;
    }

    @Override
    public void close() {
        initialized = false;
        instance = null;
    }

    public static boolean isInitialized(){
        return Objects.nonNull(instance) && instance.initialized;
    }

}