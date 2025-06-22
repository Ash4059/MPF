package com.example.MPF.Ingresess;

import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.Ingress;

import java.util.Objects;

public class SqlIngress extends Ingress {

    private static SqlIngress instance;

    public SqlIngress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public void initialize(Config config) {
        this.initialized = true;
        this.config = config;
    }

    @Override
    public void close() {
        this.initialized = false;
        instance = null;
    }

    public static SqlIngress getInstance(){
        return instance;
    }

    public static boolean isInitialized(){
        return Objects.nonNull(instance) && instance.initialized;
    }

}