package com.example.MPF.Ingresess;

import com.example.MPF.Model.Config;
import com.example.MPF.Model.Message;
import com.example.MPF.ModuleInterface.Ingress;

public class SqlIngress implements Ingress {
    private boolean initialized = false;
    private static SqlIngress instance;
    Config config;

    public SqlIngress(Config config){
        this.initialize(config);
        instance = this;
    }

    @Override
    public Message fetch() {
        return null;
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
}