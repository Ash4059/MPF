package com.example.MPF.Ingresess;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;
import com.example.MPF.ModuleInterface.Ingress;

public class KafkaIngress implements Ingress {
    private boolean initialized = false;
    private static KafkaIngress instance;
    Config config;

    public KafkaIngress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public Message fetch() {
        return null;
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
}
