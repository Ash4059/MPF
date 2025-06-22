package com.example.MPF.Egresess;

import com.example.MPF.Model.Config;
import com.example.MPF.ModuleInterface.Egress;

import java.util.Objects;

public class KafkaEgress extends Egress {

    private static KafkaEgress instance;

    public KafkaEgress(Config config){
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
        initialized = false;
        instance = null;
    }

    public static KafkaEgress getInstance(){
        return instance;
    }

    public static boolean isInitialized(){
        return Objects.nonNull(instance) && instance.initialized;
    }

}
