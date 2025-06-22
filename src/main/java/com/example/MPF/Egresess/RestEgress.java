package com.example.MPF.Egresess;

import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.Egress;

import java.util.Objects;

public class RestEgress extends Egress {

    private static RestEgress instance;

    public RestEgress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public void initialize(Config config) {
        this.config = config;
        this.initialized = true;
    }

    @Override
    public void close() {
        this.initialized = false;
        instance = null;
    }

    public static RestEgress getInstance(){
        return instance;
    }

    public static boolean isInitialized(){
        return Objects.nonNull(instance) && instance.initialized;
    }

}
