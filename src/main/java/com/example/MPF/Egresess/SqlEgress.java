package com.example.MPF.Egresess;

import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.Egress;

import java.util.Objects;

public class SqlEgress extends Egress {

    private static SqlEgress instance;

    public SqlEgress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public void initialize(Config config) {
        this.config = config;
        initialized = true;
    }

    @Override
    public void close() {
        instance = null;
        initialized = false;
    }

    public static SqlEgress getInstance(){
        return instance;
    }

    public static boolean isInitialized(){
        return Objects.nonNull(instance) && instance.initialized;
    }

}
